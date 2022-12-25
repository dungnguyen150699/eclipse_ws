package dungnt_ptit.com.securitystandard.configuration.filter.interceptor;

import dungnt_ptit.com.securitystandard.configuration.filter.annotation.RBAC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GeneralPurposeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        log.info("PreHanlder method of GeneralPurposeInterceptor ");
        HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);
        Method method = handlerMethod.getMethod();
        RBAC rbac = method.getAnnotation(RBAC.class);
        boolean hasAccess = false;

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> grantedAuthorities = authenticationToken.getAuthorities();
        List<String> userLoginRoles = grantedAuthorities.stream().map(g -> g.getAuthority()).collect(Collectors.toList());
        List<String> roleHasAccess = Arrays.stream(rbac.value()).collect(Collectors.toList());
        if(roleHasAccess.containsAll(userLoginRoles)){
            hasAccess = true;
        }
        if(!hasAccess) throw new InternalAuthenticationServiceException("user.access.deny");
        return false; // return true nó sẽ đi tiếp các Interceptor khác (SinglePurposeInterceptor)
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("PostHanlder method of GeneralPurposeInterceptor ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("AfterCompletion method of GeneralPurposeInterceptor ");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
