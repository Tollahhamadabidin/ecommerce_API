package co.istad.bidin.ecommerceite.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product code is require")
        @Size(min = 3, max = 100, message = "code must not exceed 100 character")
        String code,
        @NotBlank(message = "slug is require")
        @Size(min = 3, max = 100, message = "slug must not exceed 100 character")
        String slug,
        @NotBlank(message = "product name is require")
        String name,
        @Size(max = 500, message = "Product description must not exceed 100 character")
        String description,
        String thumbnail,
        @NotNull(message = "Unit price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal unitPrice,
        @NotNull(message = "Quantity is require")
        @Min(value = 0, message = "Quantity must be one or more")
        Integer qty,
        @NotNull(message = "Category ID is required")
        Integer categoryId
) {
}
