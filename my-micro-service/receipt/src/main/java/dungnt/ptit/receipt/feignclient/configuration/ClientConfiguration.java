package dungnt.ptit.receipt.feignclient.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dungnt.ptit.receipt.feignclient.auth.AppProperties;
import dungnt.ptit.receipt.feignclient.auth.LoginRequest;
import dungnt.ptit.receipt.feignclient.auth.TokenProvider;
import feign.RequestInterceptor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Configuration
public class ClientConfiguration{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private TokenProvider tokenProvider;

    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aWVuZHVuZ2FjMTk5eDlAZ21haWwuY29tIiwicm9sZSI6WyJBRE1JTiJdLCJleHAiOjE2NzkwNjUyODB9.6Ho_ae9mlTeAlNcIzlncDa3xDPWSxCWkQ--NhDQYJwY";

    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
                requestTemplate.header("Authorization", "Bearer " + getToken());
        };
    }

    public String getToken(){
        if(tokenProvider.validateToken(token)) return token;

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject;
        String body;
        try{
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity
                    (new LoginRequest(appProperties.getAuth().getEmail(),appProperties.getAuth().getPassword()),
                            headers);
            ResponseEntity<Object> response = restTemplate.exchange(appProperties.getAuth().getUrlAuth(),
                    HttpMethod.POST,
                    entity,
                    Object.class);
            body = objectMapper.writeValueAsString(response.getBody());
            jsonObject = new JSONObject(body);
            token = jsonObject.getJSONObject("item").getString("accessToken");
            return token;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return token;
    }

}