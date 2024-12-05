package com.task.urlshortener.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlRequest {
    @NotEmpty
    private String originalUrl;
    private String customShortCode; // optional
    private LocalDateTime expirationDate; // optional
}
