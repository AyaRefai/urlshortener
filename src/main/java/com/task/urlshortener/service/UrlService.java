package com.task.urlshortener.service;

import com.task.urlshortener.dto.UrlRequest;
import com.task.urlshortener.dto.UrlResponse;
import com.task.urlshortener.entity.Url;
import com.task.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse createShortUrl(UrlRequest request) {

        String shortCode = request.getCustomShortCode() != null ?
                request.getCustomShortCode() : generateShortCode();

        if(urlRepository.findByShortCode(shortCode).isPresent()) {
            throw new IllegalArgumentException("ShortCode already exists");
        }

        Url url = new Url();
        url.setShortCode(shortCode);
        url.setOriginalUrl(request.getOriginalUrl());
        url.setExpirationDate(request.getExpirationDate());
        url.setCreatedAt(LocalDateTime.now());

        urlRepository.save(url);
        return new UrlResponse(shortCode, request.getOriginalUrl(), request.getExpirationDate());
    }

    public String resolveShortUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found"));

        if(url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Short URL has expired");
        }

        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }

    public int getShortUrlClickCount(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found"));

        return url.getClickCount();
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
