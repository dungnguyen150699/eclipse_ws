package com.ducnguyen.authserver.controller;

import com.ducnguyen.authserver.dto.UserData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @PostMapping("/check-access-privileges")
    public boolean getData(@RequestBody UserData userData) {

        return userData.getUser().equals("username") && userData.getPassword().equals("password");
    }
}
