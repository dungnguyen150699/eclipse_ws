package myoauth2server.service;

public interface UserService {

    boolean authenUser(String email, String password);

//    boolean changePassword(TokenResetPassDTO tokenResetDTO);

}
