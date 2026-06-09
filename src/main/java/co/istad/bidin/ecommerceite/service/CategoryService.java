package co.istad.bidin.ecommerceite.service;

import co.istad.bidin.ecommerceite.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> getAllCategory (int pageNUmber, int pageSize);

    CategoryResponse getCategoryById(Integer id);

    Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int pageNumber, int pageSize);

    void softDeleteCategory(Integer id);

    void hardDeleteCategory(Integer id);

    CategoryResponse updateCategoryById(Integer id, CreateCategoryRequest categoryRequest);

}
