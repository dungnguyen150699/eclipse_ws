package myoauth2server;

import myoauth2server.pojo.entity.User;
import myoauth2server.repository.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Myoauth2ServerApplication implements CommandLineRunner {

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Myoauth2ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User()
//                .setEmail("admin1")
//                .setActive(true)
//                .setRole("ADMIN")
//                .setPassword(passwordEncoder.encode("1"));
//        userJpa.save(user);
    }
}
