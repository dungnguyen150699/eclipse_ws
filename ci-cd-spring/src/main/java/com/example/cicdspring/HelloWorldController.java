package com.example.cicdspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cicd")
class HelloWorldController{
    @GetMapping("")
    public String cicdTest(){
        return "Hello this is test";
    }
}