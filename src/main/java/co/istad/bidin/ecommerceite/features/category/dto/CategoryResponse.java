package co.istad.bidin.ecommerceite.features.category.dto;

import co.istad.bidin.ecommerceite.features.category.Category;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDelete,
        Category parentCategory
) {
}
