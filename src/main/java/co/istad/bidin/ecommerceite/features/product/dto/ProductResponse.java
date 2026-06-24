package co.istad.bidin.ecommerceite.features.product.dto;

import co.istad.bidin.ecommerceite.features.category.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.features.category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record ProductResponse(
       String code,
       String slug,
       String name,
       String description,
       String thumbnail,
       BigDecimal unitPrice,
       Integer qty,
       Boolean isAvailable,
       Boolean isDeleted,
       CategorySnippetResponse category
) {
}
