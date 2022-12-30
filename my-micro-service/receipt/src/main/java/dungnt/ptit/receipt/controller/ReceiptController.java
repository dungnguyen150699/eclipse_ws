package dungnt.ptit.receipt.controller;

import dungnt.ptit.receipt.feignclient.BookModuFeignClient;
import dungnt.ptit.receipt.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receipt")
public class ReceiptController {
    @Autowired
    private BookModuFeignClient bookModuFeignClient;

    @GetMapping("/getBook")
    public String getBook(){
        return bookModuFeignClient.getBookName("My Book");
    }

    @GetMapping("/postBook")
    public String postBook(){
        return bookModuFeignClient.postBook(new Book("my Post Book","NTD"));
    }
}
