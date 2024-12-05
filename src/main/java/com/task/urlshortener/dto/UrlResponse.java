package com.task.urlshortener.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlResponse {

    private String shortCode;
    private String originalUrl;
    private LocalDateTime expirationDate;

    public UrlResponse(String shortCode, String originalUrl, LocalDateTime expirationDate) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.expirationDate = expirationDate;
    }
}
