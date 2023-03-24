package dungnt_ptit.com.securitystandard.service.impl;

import dungnt_ptit.com.securitystandard.service.DI_Interface;
import org.springframework.stereotype.Service;

@Service
public class DI_InterfaceImpl1 implements DI_Interface {
    @Override
    public void sayHello() {
        System.out.println("Hello 1");
    }
}
