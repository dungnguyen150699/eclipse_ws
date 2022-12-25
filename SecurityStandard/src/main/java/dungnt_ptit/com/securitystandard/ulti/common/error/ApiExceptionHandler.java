package dungnt_ptit.com.securitystandard.ulti.common.error;

import com.google.gson.Gson;
import dungnt_ptit.com.securitystandard.ulti.common.ApiError;
import dungnt_ptit.com.securitystandard.ulti.common.ApiMessage;
import dungnt_ptit.com.securitystandard.ulti.common.i18n.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @Autowired
    private MessageService messageService;
    @Autowired
    private Gson gson;

    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity handleServerError(Exception ex) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        ApiMessage response = ApiMessage.builder().success(false)
                .message(messageService.getMessage(ApiError.INTERNAL_SERVER_ERROR.getCode()))
                .code(ApiError.INTERNAL_SERVER_ERROR.getCode()).build();
    //    --> Có thể dùng cái này thay thế
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.add("Content-Type", "application/json; charset=utf-8");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));
        return new ResponseEntity<>(gson.toJson(response),headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody ResponseEntity handleNotFoundException(Exception ex) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));

        ApiMessage response = ApiMessage.builder().success(false)
                .message(messageService.getMessage(ex.getMessage()))
                .code(ApiError.NOT_FOUND.getCode()).build();
        return new ResponseEntity<>(gson.toJson(response),headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public @ResponseBody ResponseEntity handleInternalAuthenticationServiceException(Exception ex) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));

        ApiMessage response = ApiMessage.builder().success(false)
                .message(messageService.getMessage(ex.getMessage()))
                .code(ApiError.ACCESS_DENY.getCode()).build();
        return new ResponseEntity<>(gson.toJson(response),headers, HttpStatus.FORBIDDEN);
    }
}
