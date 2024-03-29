package dungnt.ptit.bookmodul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookModulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookModulApplication.class, args);
    }

}
