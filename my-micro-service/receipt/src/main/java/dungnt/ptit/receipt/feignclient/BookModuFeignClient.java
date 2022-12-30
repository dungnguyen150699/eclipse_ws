package dungnt.ptit.receipt.feignclient;

import dungnt.ptit.receipt.pojo.Book;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Headers({
        "Accept: application/json; charset=utf-8",
        "Content-Type: application/json" })
@FeignClient(value = "book-modul-feign-client", url = "http://localhost:8082/book/")
public interface BookModuFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    String getBookName(@RequestParam(name = "nameBook") String name);

    @Headers({
            "Accept: application/json; charset=utf-8",
            "Content-Type: application/json" })
    @RequestMapping(method = RequestMethod.POST, value = "/post")
    String postBook(@RequestBody Book book);
}
