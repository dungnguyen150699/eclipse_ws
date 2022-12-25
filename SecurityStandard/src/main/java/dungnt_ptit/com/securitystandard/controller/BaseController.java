package dungnt_ptit.com.securitystandard.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dungnt_ptit.com.securitystandard.configuration.filter.annotation.RBAC;
import dungnt_ptit.com.securitystandard.pojo.dto.UserDTO;
import dungnt_ptit.com.securitystandard.pojo.entity.Role;
import dungnt_ptit.com.securitystandard.pojo.entity.User;
import dungnt_ptit.com.securitystandard.pojo.request.user.UserLoginRequest;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.ulti.common.mapper.mapStruct.UserMapStruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Controller
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class BaseController {
    @Autowired private UserRepository userRepository;
    @Autowired private UserMapStruct userMapStruct;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/login-test")
    @RBAC({"USER"})
    public @ResponseBody
    ResponseEntity login(HttpSession session) {
        User user = User.class.cast(session.getAttribute("user"));
        return null;
    }

    @GetMapping("/interceptor-spring-security")
//    @PreAuthorize("hasRole('USER')") , @RolesAllowed("USER"), @Secured("USER")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')") // Cái này thực hiện trước khi gọi method
//    @PostAuthorize("hasAnyAuthority('USER')") // Cái này thực hiện sau khi method kết thúc tức là sau khi nó trả api mẹ r
    public @ResponseBody void interceptorSpring(HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        UserDTO userDTO = userMapStruct.toDto(user);
        response.setHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(objectMapper.writeValueAsString(userDTO));
    }

//    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
//    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
//    @RolesAllowed({ "ROLE_VIEWER", "ROLE_EDITOR" })
    @PreAuthorize("hasAnyAuthority({'USER'})")
    @GetMapping("/interceptor-spring-security1")
    public void interceptorSpringSecurity(HttpServletResponse response,HttpSession session) throws IOException {
        User user = User.class.cast(session.getAttribute("user"));
        String result = objectMapper.writeValueAsString(userMapStruct.toDto(user));
        response.addHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(result);
//        return null;
    }

    @GetMapping("/token/refresh")
    @ResponseBody
    public void refreshToken(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String authriztionHeader = request.getHeader(AUTHORIZATION);
        if (authriztionHeader != null && authriztionHeader.startsWith("Bearer ")) { // Nếu thế cái Filter bên kia thừa mẹ rồi
            try {
                String refresh_token = authriztionHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userRepository.loadUserByName(username).get();
                session.setAttribute("user",user);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map map = new HashMap<String, String>();
                map.put("access_token", access_token);
                map.put("refresh_token", refresh_token);
                response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().println(objectMapper.writeValueAsString(map));
            } catch (Exception exception) {
                exception.printStackTrace();
                log.error("Error loggin in: {}", exception.getMessage());
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                Map map = new HashMap<String, String>();
                map.put("error", exception.getMessage());
                response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().println(objectMapper.writeValueAsString(map));
            }
        } else {
            throw new RuntimeException("Refresh toke is missing");
        }
    }
}