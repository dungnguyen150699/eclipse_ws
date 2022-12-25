package vn.viettel.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.viettel.app.service.MessageService;

@SpringBootApplication
public class SmartMotorLandingPageApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(SmartMotorLandingPageApplication.class, args);
    }

    @Autowired
    private MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(messageService.getMessage("error.code.dto.null"));
    }
}
