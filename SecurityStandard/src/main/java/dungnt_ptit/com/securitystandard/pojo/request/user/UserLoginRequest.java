package dungnt_ptit.com.securitystandard.pojo.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

// Bỏ qua cảnh báo các thuộc tính ko mapping
@AllArgsConstructor
@Data
@Accessors(chain = true,fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginRequest {
    private String username;
    private String password;
}
