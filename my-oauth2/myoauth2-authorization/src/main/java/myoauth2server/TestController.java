package myoauth2server;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@EnableResourceServer
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "oke";
    }
}
