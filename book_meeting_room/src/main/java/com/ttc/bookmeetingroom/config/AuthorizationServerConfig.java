package com.ttc.bookmeetingroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${security.oath2.client-id}")
    private String CLIEN_ID;

    @Value("${security.oath2.client-secret}")
    private String CLIENT_SECRET;

    @Value("${security.oath2.grant-type}")
    private String GRANT_TYPE;

    @Value("${security.oath2.authorization-code}")
    private String AUTHORIZATION_CODE;

    @Value("${security.oath2.refresh-token}")
    private String REFRESH_TOKEN;

    @Value("${security.oath2.implicit}")
    private String IMPLICIT;

    @Value("${security.oath2.scope-read}")
    private String SCOPE_READ;

    @Value("${security.oath2.scope-write}")
    private String SCOPE_WRITE;

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60;

    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory()
                .withClient(CLIEN_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager) // Authen first --> get userName + password (requestParam)
                .tokenStore(tokenStore) // TokenStore secondary -> I dont know but if Token Store first it take clientId to userName...
                .tokenEnhancer(tokenEnhancer());
        endpoints.exceptionTranslator(exception -> {
            if (exception instanceof OAuth2Exception) {
                OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
                return ResponseEntity
                        .status(oAuth2Exception.getHttpErrorCode())
                        .body(new CustomOauthException(oAuth2Exception.getMessage(), oAuth2Exception.getHttpErrorCode()));
            } else {
                throw exception;
            }
        });
    }
}