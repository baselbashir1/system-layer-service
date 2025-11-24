package com.task.systemlayer.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BundleResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}