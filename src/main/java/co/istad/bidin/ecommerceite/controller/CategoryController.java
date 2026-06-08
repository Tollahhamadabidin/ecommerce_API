package co.istad.bidin.ecommerceite.controller;

import co.istad.bidin.ecommerceite.dto.CategoryPageResponse;
import co.istad.bidin.ecommerceite.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
import co.istad.bidin.ecommerceite.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.createCategory(createCategoryRequest);
    }

    @GetMapping
    public CategoryPageResponse getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return categoryService.getAllCategory(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDelete(@PathVariable Integer id) {
        categoryService.hardDeleteCategory(id);
    }
    @PutMapping("/{id}")
    public CategoryResponse softDelete(@PathVariable Integer id) {
        return categoryService.softDeleteCategory(id);
    }


//    @PatchMapping("/{id}")
//    public CategoryResponse updateCategory(
//            @PathVariable Integer id,
//            @Valid @RequestBody UpdateCategoryRequest request
//    ) {
//        return categoryService.updateCategory(id, request);
//    }


//    @GetMapping("/{id}/subcategories")
//    public List<CategoryResponse> getSubcategories(@PathVariable Integer id) {
//        return categoryService.getSubcategoriesByParentId(id);
//    }
}
