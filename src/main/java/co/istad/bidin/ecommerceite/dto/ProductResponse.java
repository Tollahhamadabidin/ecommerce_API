package co.istad.bidin.ecommerceite.dto;

import java.math.BigDecimal;

public record ProductResponse(
       Integer id,
       String code,
       String slug,
       String name,
       String description,
       String thumbnail,
       BigDecimal unitPrice,
       Integer qty,
       Boolean isAvailable,
       CategoryResponse category
) {
}
