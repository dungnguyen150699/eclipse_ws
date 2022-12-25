package com.ttc.bookmeetingroom.service.impl;

import com.ttc.bookmeetingroom.common.error.BadRequestException;
import com.ttc.bookmeetingroom.dto.TokenResetPassDTO;
import com.ttc.bookmeetingroom.model.User;
import com.ttc.bookmeetingroom.repository.UserRepository;
import com.ttc.bookmeetingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean authenUser(String email, String password) {
        return userRepository.authenUser(email, password);
    }

    @Override
    public boolean changePassword(TokenResetPassDTO tokenResetDTO) {
        return userRepository.changePassword(tokenResetDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(s);
        if (user == null) {
            throw new BadRequestException("Invalid password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }
}
