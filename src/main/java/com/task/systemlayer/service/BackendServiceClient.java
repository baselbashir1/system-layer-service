package com.task.systemlayer.service;

import com.task.systemlayer.dto.request.BundleRequest;
import com.task.systemlayer.dto.response.BundleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BackendServiceClient {

    private final WebClient webClient;

    public BackendServiceClient(@Value("${backend.service.url}") String backendUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(backendUrl)
                .build();
    }

    public Mono<BundleResponse> getBundle(Long id, String correlationId) {
        return webClient.get()
                .uri("/bundles/{id}", id)
                .header("X-Correlation-ID", correlationId)
                .retrieve()
                .bodyToMono(BundleResponse.class);
    }

    public Mono<String> createBundle(BundleRequest request, String correlationId) {
        return webClient.post()
                .uri("/bundles")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-Correlation-ID", correlationId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> updateBundle(Long id, BundleRequest request, String correlationId) {
        return webClient.patch()
                .uri("/bundles/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-Correlation-ID", correlationId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteBundle(Long id, String correlationId) {
        return webClient.delete()
                .uri("/bundles/{id}", id)
                .header("X-Correlation-ID", correlationId)
                .retrieve()
                .bodyToMono(String.class);
    }
}