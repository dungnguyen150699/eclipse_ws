package dungnt_ptit.com.securitystandard.ulti.common.i18n;


public interface MessageService {
    public String getMessage(String code);

    public String getMessage(String code, Object... args);
}
