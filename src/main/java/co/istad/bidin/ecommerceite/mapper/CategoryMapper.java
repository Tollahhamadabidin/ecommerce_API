package co.istad.bidin.ecommerceite.mapper;

import co.istad.bidin.ecommerceite.domain.Category;
import co.istad.bidin.ecommerceite.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
//    return type = taget
//    parameter = source
Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
CategoryResponse mapCategoryToCategoryResponse(Category category);

}

