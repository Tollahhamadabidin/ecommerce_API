package co.istad.bidin.ecommerceite.mapper;

import co.istad.bidin.ecommerceite.domain.Category;
import co.istad.bidin.ecommerceite.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
import co.istad.bidin.ecommerceite.dto.UpdateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpdateCategoryMapper {
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse mapCategoryToCategoryResponse(Category category);
    void updateCategoryFromRequest(UpdateCategory request, @MappingTarget Category category);
}
