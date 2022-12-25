package vn.viettel.app.config.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateException extends Exception {
    private Object data;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Object data) {
        super(message);
        this.data = data;
    }

}
