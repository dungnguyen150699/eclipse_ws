package myoauth2server.repository;


import myoauth2server.pojo.entity.User;

public interface UserRepository {

    boolean authenUser(String email, String password);

    User getUserByEmail(String email);

//    boolean changePassword(TokenResetPassDTO tokenResetDTO);
}
