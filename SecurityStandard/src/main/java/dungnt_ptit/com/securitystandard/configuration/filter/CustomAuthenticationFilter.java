package dungnt_ptit.com.securitystandard.configuration.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
//@Component --> ko cần tạo bean cho thằng này
// Bản chất thằng này kế thừa từ thằng UsernamePasswordAuthenticationFilter nó tự filter url = /login
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final Gson gson;
    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,Gson gson){
        this.authenticationManager = authenticationManager;
        this.gson = gson;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        // Cách 1 lấy qua parameter
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = null;
//        log.info("Username is: {}", username); log.info("Password is: {}", password);
//        authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        // Cách 2 lấy qua body sử dụng gson
        User user = null;
        try {
            user = gson.fromJson(request.getReader(),User.class);
            log.info("Username is: {}", user.getUsername()); log.info("Password is: {}", user.getPassword());
            authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //Spring tự thêm prefix ROLE_ vào quyền user của nó ko muốn thì mình dùng user của mình
        // https://stackoverflow.com/questions/21620076/spring-security-remove-rolevoter-prefix
        User user = (User) authentication.getPrincipal();
//        dungnt_ptit.com.securitystandard.pojo.entity.User user = (dungnt_ptit.com.securitystandard.pojo.entity.User) authentication.getPrincipal();
        Algorithm algoritgm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000 * 24))
//                .withClaim("roles", user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algoritgm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 60 * 1000 * 24))
//                .withClaim("roles",user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algoritgm);
//        response.setHeader("access_token",access_token);
//        response.setHeader("refresh_token",refresh_token);

        Map map = new HashMap<String,String>();
        map.put("access_token",access_token);
        map.put("refresh_token",refresh_token);
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(map));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
