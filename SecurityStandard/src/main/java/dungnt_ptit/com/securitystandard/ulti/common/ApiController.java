package dungnt_ptit.com.securitystandard.ulti.common;

import com.google.gson.Gson;
//import dungnt_ptit.com.securitystandard.ulti.common.i18n.Translator;
import dungnt_ptit.com.securitystandard.ulti.common.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ApiController {

    @Autowired
    private Gson gson;

//    @Autowired
//    private Translator translator;


    protected ResponseEntity<Object> ok(Object data) {
        ApiMessage apiMessage = ApiMessage
                .builder()
                .success(true)
                .code("200")
                .message("OK")
                .data(data)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(apiMessage, headers,HttpStatus.OK);
    }

}
