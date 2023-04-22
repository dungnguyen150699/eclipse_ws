package com.ducnguyen.service.controller;

import com.ducnguyen.service.dto.Data;
import com.ducnguyen.service.dto.UserData;
import com.ducnguyen.service.services.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/get-data")
    public List<Data> getData(@RequestBody UserData userData) {
        return homeService.getData(userData.getUser(), userData.getPassword());
    }
}
