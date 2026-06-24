package co.istad.bidin.ecommerceite.features.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "product name is require")
        @Size(max = 255)
        String name,
        @Size (max = 500)
        String description,
        @Size(max = 255)
        String thumbnail,
        @NotNull(message = "Unit price is required")
        @Min(0)
        BigDecimal unitPrice,
        @NotNull(message = "Quantity is require")
        @Min(0)
        Integer qty,
        @NotNull(message = "Category ID is required")
        @Positive
        Integer categoryId
) {
}
