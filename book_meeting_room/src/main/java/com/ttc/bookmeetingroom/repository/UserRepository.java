package com.ttc.bookmeetingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttc.bookmeetingroom.dto.TokenResetPassDTO;
import com.ttc.bookmeetingroom.model.Location;
import com.ttc.bookmeetingroom.model.User;

public interface UserRepository{

    boolean authenUser(String email, String password);

    User getUserByEmail(String email);

    boolean changePassword(TokenResetPassDTO tokenResetDTO);
}
