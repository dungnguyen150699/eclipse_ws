package myoauth2clienttogoogleauthen.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller_test {
    @GetMapping("/")
    public String helloWorld(HttpServletResponse reponse) throws IOException{
//    	reponse.getOutputStream().println("<a href='/logout'> LogOut</a>");
        return "<a href='/logout'> LogOut</a>";
    }

    @GetMapping("/restricted")
    public String restricted(){
        return "to see this texxt you need to be logged in!";
    }

}
