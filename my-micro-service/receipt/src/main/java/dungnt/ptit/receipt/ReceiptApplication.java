package dungnt.ptit.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReceiptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiptApplication.class, args);
    }

}
