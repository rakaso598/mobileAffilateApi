package com.example.demo.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 외부 API 호출
    public Map<String, Object> getProductData(String apiUrl) throws IOException {
        // API 호출
        String xmlResponse = restTemplate.getForObject(apiUrl, String.class);

        // XML을 JSON으로 변환
        XmlMapper xmlMapper = new XmlMapper();
        Map<String, Object> result = xmlMapper.readValue(xmlResponse, Map.class);

        // XML -> JSON 변환 결과 가공 (예시로 첫 번째 제품만 추출)
        List<Map<String, Object>> products = (List<Map<String, Object>>) result.get("products");
        if (!products.isEmpty()) {
            return products.get(0);  // 첫 번째 제품 반환
        }
        return null;
    }
}
