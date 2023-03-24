package dungnt_ptit.com.securitystandard.service.impl;

import dungnt_ptit.com.securitystandard.pojo.entity.User;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.service.DI_Interface;
import dungnt_ptit.com.securitystandard.service.UserService;
import dungnt_ptit.com.securitystandard.ulti.common.error.NotFoundException;
import dungnt_ptit.com.securitystandard.ulti.common.i18n.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Qualifier(value = "DI_InterfaceImpl1")
    private DI_Interface di_interface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // My user
        User user = userRepository.loadUserByName(username).orElseThrow(() -> new NotFoundException("user.notfound.username"));
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().stream().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName()) ));
        return new org.springframework.security.core.userdetails.User
                (       user.getUsername(), user.getPassword(), user.isEnabled(),
                        user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                        user.isAccountNonLocked(),authorities
                );
    }


}
