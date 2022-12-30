package dungnt.ptit.bookmodul.controller;

import dungnt.ptit.bookmodul.pojo.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    @GetMapping("get")
    public String getBook(@RequestParam(name = "nameBook") String name){
        return name;
    }

    @PostMapping("post")
    public String postBook(@RequestBody Book book){
        return book.toString();
    }
}
