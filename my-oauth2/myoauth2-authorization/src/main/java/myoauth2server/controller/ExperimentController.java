package myoauth2server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiment")
public class ExperimentController {

    @GetMapping("")
    public String getTest(){
        return "success";
    }
}
