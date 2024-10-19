package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apiUrl}")
    private String apiUrl;

    public String getApiData() {
        String url = apiUrl;  // 단순한 GET 링크
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); // XML 형식으로 응답받음
        return response.getBody();  // XML 데이터를 그대로 반환
    }
}
