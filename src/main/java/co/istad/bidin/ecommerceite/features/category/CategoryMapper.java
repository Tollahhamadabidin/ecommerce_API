package co.istad.bidin.ecommerceite.features.category;

import co.istad.bidin.ecommerceite.features.category.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
//    return type = taget
//    parameter = source
Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
CategoryResponse mapCategoryToCategoryResponse(Category category);

}

