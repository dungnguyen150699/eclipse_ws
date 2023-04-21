package com.ducnguyen.service.services;

import com.ducnguyen.service.dto.Data;
import com.ducnguyen.service.dto.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Value("${auth.server.baseUrl}")
    String authServerBaseUrl;

    public Boolean getAccess(String username, String password) {

        try {
            String checkAccessUrl = authServerBaseUrl + "/home/check-access-privileges";
            System.out.println(checkAccessUrl);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            UserData uData = new UserData(username, password);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(uData);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody,headers);
            return restTemplate.postForObject(checkAccessUrl, entity, Boolean.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Data> getData(String username, String password) {
        // check access privileges
        if (!this.getAccess(username, password)) {
            return null;
        }

        List<Data> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Data dt = new Data();
            dt.setId((long) i);
            dt.setValue("vl number " + i);
            res.add(dt);
        }
        return res;
    }
}
