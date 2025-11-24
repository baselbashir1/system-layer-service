package com.task.systemlayer.dto.request;

import java.math.BigDecimal;

public record BundleRequest(
        String name,
        String description,
        BigDecimal price
) {
}