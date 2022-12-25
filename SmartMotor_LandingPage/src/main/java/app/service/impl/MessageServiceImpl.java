package vn.viettel.app.service.impl;

import app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private com.viettel.mtracking.charging.config.LocaleResolver localeResolver;

    @Override
    public String getMessage(String code) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(code, null, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) .getRequest();
        return messageSource.getMessage(code, null, localeResolver.resolveLocale(request) );
    }

    @Override
    public String getMessage(String code,  Object... args) {
        if(RequestContextHolder.getRequestAttributes() == null) {
            return messageSource.getMessage(code, args, new Locale("vi"));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) .getRequest();
        return messageSource.getMessage(code, args, localeResolver.resolveLocale(request));
    }
}
