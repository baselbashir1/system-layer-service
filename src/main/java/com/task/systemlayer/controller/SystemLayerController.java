package com.task.systemlayer.controller;

import com.task.systemlayer.dto.request.BundleRequest;
import com.task.systemlayer.dto.response.BundleResponse;
import com.task.systemlayer.service.BackendServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/bundles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SystemLayerController {

    private final BackendServiceClient backendServiceClient;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BundleResponse>> getBundleById(@PathVariable Long id, @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {
        return backendServiceClient.getBundleById(id, correlationId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createBundle(@RequestBody BundleRequest request, @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {
        return backendServiceClient.createBundle(request, correlationId)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<String>> updateBundle(@PathVariable Long id, @RequestBody BundleRequest request, @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {
        return backendServiceClient.updateBundle(id, request, correlationId)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteBundle(@PathVariable Long id, @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {
        return backendServiceClient.deleteBundle(id, correlationId)
                .map(ResponseEntity::ok);
    }
}