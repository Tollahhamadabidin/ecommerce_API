package co.istad.bidin.ecommerceite.dto;

import java.util.List;

public record CategoryPageResponse(
        List<CategoryResponse> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
