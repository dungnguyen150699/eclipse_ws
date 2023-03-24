package dungnt.ptit.receipt.feignclient;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Headers({"Accept: application/json; charset=utf-8",
        "Content-Type: application/json" })
@FeignClient(value = "book-modul-feign-client", url = "http://localhost:8083/user/")
public interface UserFeignClient {

//    @Headers({"Authorization:" + })
    @RequestMapping(method = RequestMethod.GET, value = "/infor")
    String userInfor();
}
