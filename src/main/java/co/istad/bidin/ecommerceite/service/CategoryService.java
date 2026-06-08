package co.istad.bidin.ecommerceite.service;

import co.istad.bidin.ecommerceite.dto.CategoryPageResponse;
import co.istad.bidin.ecommerceite.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
import co.istad.bidin.ecommerceite.dto.UpdateCategory;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    CategoryPageResponse getAllCategory(int pageNumber, int pageSize);

    CategoryResponse getCategoryById(Integer id);

    List<CategoryResponse> getSubcategoriesByParentId(Integer parentId);
    void hardDeleteCategory(Integer id);

    // PUT soft delete (toggle isDelete = true)
    CategoryResponse softDeleteCategory(Integer id);

    // PATCH update name, icon, description
//    CategoryResponse updateCategory(Integer id, UpdateCategory request);
}
