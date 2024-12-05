package com.task.urlshortener;

import com.task.urlshortener.dto.UrlRequest;
import com.task.urlshortener.repository.UrlRepository;
import com.task.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UrlshortenerApplicationTests {

	@Test
	void contextLoads() {
	}

	private final UrlRepository urlRepository = mock(UrlRepository.class);
	private final UrlService urlService = new UrlService(urlRepository);

	@Test
	void createShortUrl() {
		UrlRequest request = new UrlRequest();
		request.setOriginalUrl("https://example.com");
		request.setExpirationDate(LocalDateTime.now().plusDays(7));

		when(urlRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArguments()[0]);

		var response = urlService.createShortUrl(request);
		assertEquals("https://example.com", response.getOriginalUrl());
	}

}
