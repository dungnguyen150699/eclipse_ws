package dungnt_ptit.com.securitystandard.configuration.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dungnt_ptit.com.securitystandard.pojo.entity.User;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

// interceptor
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login")){
            // Nếu user đang cố login ko làm gì cả tiếp tục gọi filter lần tới thôi
            // Mặc định nếu ko login thành công thì Security sẽ bắn ra authentication rồi
            filterChain.doFilter(request,response);
        }else{
            String authriztionHeader = request.getHeader(AUTHORIZATION);
            if(authriztionHeader != null && authriztionHeader.startsWith("Bearer ")){ // Nếu thế cái Filter bên kia thừa mẹ rồi
                try{
                    String token = authriztionHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    String username = decodedJWT.getSubject();
                    String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorties = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorties.add(new SimpleGrantedAuthority(role));
                    });

                    // UserDetail
                    org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(username,null,authorties);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetail,null,authorties);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Cái này cho pass nhờ vào token
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    log.error("Error loggin in: {}",exception.getMessage());
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map map = new HashMap<String,String>();
                    map.put("error",exception.getMessage());
                    response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    response.getOutputStream()
                            .println(objectMapper.writeValueAsString(map));
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
