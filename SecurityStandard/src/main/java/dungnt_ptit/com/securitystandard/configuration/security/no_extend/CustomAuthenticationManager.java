package dungnt_ptit.com.securitystandard.configuration.security.no_extend;

import dungnt_ptit.com.securitystandard.pojo.entity.Role;
import dungnt_ptit.com.securitystandard.pojo.entity.User;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.ulti.common.i18n.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;

    public CustomAuthenticationManager(PasswordEncoder passwordEncoder,MessageService messageService, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        User user = userRepository.loadUserByName(username).orElseThrow(() -> new InternalAuthenticationServiceException(
                messageService.getMessage("user.notfound.username")));
        if(!passwordEncoder.matches(password,user.getPassword()))  throw new InternalAuthenticationServiceException(messageService.getMessage("user.notfound.username"));

        // add Role Authority
        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        roles.stream().forEach(r -> grantedAuths.add(new SimpleGrantedAuthority(r.getName()) ));

        // Bước này xem lại UserDetail (thật ra bước này thay thế UserDetail thôi)
        UserDetails authenticationToken = new org.springframework.security.core.userdetails.User( user.getUsername(), user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),grantedAuths);
//         chú ý bước này thiếu check hết hạn tài khoản và mấy thứ lấy từ db kia ra nhé( isEnabled, accountNonExpired,...)
        return new UsernamePasswordAuthenticationToken(authenticationToken,password, grantedAuths);
    }
}
