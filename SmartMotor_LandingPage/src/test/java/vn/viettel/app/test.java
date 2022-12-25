package vn.viettel.app;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.viettel.app.service.LandingPageProductService;
import vn.viettel.app.service.MessageService;

import javax.persistence.Column;

public class test {
    @Autowired
    private MessageService messageService;

    @Autowired
    private LandingPageProductService lp_p;

    public test(){};

    public void testx(){
        System.out.println(messageService.getMessage("error.code.success"));
    }

    public static void main(String[] args) {
        new test().lp_p.findAll();
    }
}
