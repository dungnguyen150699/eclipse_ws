package dungnt_ptit.com.securitystandard.ulti.common.i18n;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LocaleResolver extends AcceptHeaderLocaleResolver {

    /**
     * The locale.
     */
    private final List<Locale> locales = Arrays.asList(new Locale("en"), new Locale("vi"));

    /**
     * Resolve locale.
     *
     * @param request the request
     * @return the locale
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (request.getHeader("Accept-Language") == null || request.getHeader("Accept-Language").isEmpty()) {
            return new Locale("vi");
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
        return Locale.lookup(list, locales);
    }
}
