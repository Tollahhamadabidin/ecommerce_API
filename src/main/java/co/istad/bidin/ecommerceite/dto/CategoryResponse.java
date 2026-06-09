package co.istad.bidin.ecommerceite.dto;

import co.istad.bidin.ecommerceite.domain.Category;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDelete,
        Category parentCategory
) {
}
