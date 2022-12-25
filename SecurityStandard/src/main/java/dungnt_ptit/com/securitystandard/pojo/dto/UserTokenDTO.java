package dungnt_ptit.com.securitystandard.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@Data
@JsonInclude(value = NON_NULL, content = NON_EMPTY) // Bỏ null , bỏ empty()
@Accessors(chain = true,fluent = true)
public class UserTokenDTO implements Serializable {
    private User user;
    private String accessToken, refreshToken;
}
