package co.istad.bidin.ecommerceite.features.category;

import co.istad.bidin.ecommerceite.features.category.dto.CategoryResponse;
import co.istad.bidin.ecommerceite.features.category.dto.CreateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return categoryService.getAllCategory(pageNumber, pageSize);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/subcategories")
    public Page<CategoryResponse> getSubCategoriesByMainId (
            @RequestParam Integer parentId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return categoryService.getSubCategoriesByMainId(parentId, pageNumber, pageSize);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/soft-delete")
    public void softDeleteCategory(@PathVariable Integer id) {
        categoryService.softDeleteCategory(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDeleteCategory(@PathVariable Integer id) {
        categoryService.hardDeleteCategory(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id
            , @RequestBody CreateCategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }

}
