package com.example.demo.controller;

import com.example.demo.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService priceTrendService) {
        this.apiService = priceTrendService;
    }

    @GetMapping("/api")
    public String getPriceTrend(
            @RequestParam int page,
            @RequestParam int perPage
    ) {
        return apiService.getPriceTrend(page, perPage);
    }
}
