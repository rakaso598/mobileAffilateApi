package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {

    @Value("${api.key.encoded}")
    private String encodedApiKey;

    @Value("${api.key.decoded}")
    private String decodedApiKey;

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPriceTrend(int page, int perPage) {
        // API 호출 URL 구성
        String url = "https://api.odcloud.kr/api/15010720/v1/uddi:207c866c-6ed3-4faa-bea5-b0c1a3446937_202003231042";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("page", page)
                .queryParam("perPage", perPage)
                .queryParam("serviceKey", encodedApiKey); // 인코딩된 키 사용

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + decodedApiKey); // 디코딩된 키를 Authorization 헤더에 포함

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 요청
        ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
