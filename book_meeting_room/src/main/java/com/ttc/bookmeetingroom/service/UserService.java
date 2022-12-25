package com.ttc.bookmeetingroom.service;

import com.ttc.bookmeetingroom.dto.TokenResetPassDTO;

public interface UserService {

    boolean authenUser(String email, String password);

    boolean changePassword(TokenResetPassDTO tokenResetDTO);

}
