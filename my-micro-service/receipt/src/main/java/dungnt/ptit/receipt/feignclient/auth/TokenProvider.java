package dungnt.ptit.receipt.feignclient.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final Algorithm algorithm;

    // Từ spring 4.3 nếu tiêm 1 @Config có thể bỏ qua từ khóa @Autowired
    public TokenProvider(AppProperties appProperties) {
        this.algorithm = Algorithm.HMAC256(appProperties.getAuth().getTokenSecret());
    }

    public boolean validateToken(String authToken) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT ;
        Date dateExpire;
        try {
            decodedJWT = verifier.verify(authToken);
            dateExpire = decodedJWT.getExpiresAt();
            if(new Date().before(dateExpire)) return true;
        } catch (Exception exception){
            logger.error(exception.getMessage());
        }
        return false;
    }
}
