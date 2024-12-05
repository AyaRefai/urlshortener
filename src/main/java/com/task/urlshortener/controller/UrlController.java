package com.task.urlshortener.controller;

import com.task.urlshortener.dto.UrlRequest;
import com.task.urlshortener.dto.UrlResponse;
import com.task.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlRequest request) {
        UrlResponse response = urlService.createShortUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<String> resolveShortUrl(@PathVariable("shortCode") String shortCode) {
        String originalUrl = urlService.resolveShortUrl( shortCode );
        return ResponseEntity.ok(originalUrl);
    }

}
