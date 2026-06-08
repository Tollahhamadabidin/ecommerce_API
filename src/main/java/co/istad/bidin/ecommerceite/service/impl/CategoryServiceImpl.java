    package co.istad.bidin.ecommerceite.service.impl;

    import co.istad.bidin.ecommerceite.domain.Category;
    import co.istad.bidin.ecommerceite.dto.CategoryPageResponse;
    import co.istad.bidin.ecommerceite.dto.CategoryResponse;
    import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
    import co.istad.bidin.ecommerceite.dto.UpdateCategory;
    import co.istad.bidin.ecommerceite.mapper.CategoryMapper;
    import co.istad.bidin.ecommerceite.mapper.UpdateCategoryMapper;
    import co.istad.bidin.ecommerceite.repository.CategoryRepository;
    import co.istad.bidin.ecommerceite.repository.UpdateCategoryRepository;
    import co.istad.bidin.ecommerceite.service.CategoryService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    @Service
    @RequiredArgsConstructor
    public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;

        private final CategoryMapper categoryMapper;

        private final UpdateCategoryMapper updateCategoryFromRequest;

        @Override
        public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
            // Validate categoryName
            boolean isExisting = categoryRepository.existsByName(createCategoryRequest.name());
            if (isExisting)
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");

            // Validate parent category
            Category parentCategory = null;
            if (createCategoryRequest.parentCategoryId() != null) {
                parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent category has not been found"));
            }

            Category category = categoryMapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
//            system generated data
            category.setIsDelete(false);
            category.setParentCategory(parentCategory);

            Category savedCategory = categoryRepository.save(category);


            CategoryResponse parentCategoryResponse = null;
            if (parentCategory != null) {
                parentCategoryResponse = CategoryResponse.builder()
                        .id(parentCategory.getId())
                        .name(parentCategory.getName())
                        .description(parentCategory.getDescription())
                        .icon(parentCategory.getIcon())
                        .isDeleted(parentCategory.getIsDelete())
                        .build();
            }

            return categoryMapper.mapCategoryToCategoryResponse(category);
        }

        @Override
        public CategoryPageResponse getAllCategory(int pageNumber, int pageSize) {
            Page<Category> page = categoryRepository.findAll(PageRequest.of(pageNumber, pageSize));

            List<CategoryResponse> content = page.getContent()
                    .stream()
                    .map(categoryMapper::mapCategoryToCategoryResponse)
                    .toList();

            return new CategoryPageResponse(
                    content,
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages()
            );
        }

//        GET by ID
        @Override
        public CategoryResponse getCategoryById(Integer id) {
                Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
                return categoryMapper.mapCategoryToCategoryResponse(category);
        }

        @Override
        public List<CategoryResponse> getSubcategoriesByParentId(Integer parentId) {
//            categoryRepository.findById(parentId)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent category not found"));
//
//            return categoryRepository.(parentId)
//                    .stream()
//                    .map(categoryMapper::mapCategoryToCategoryResponse)
//                    .toList();
            return null;

        }

        @Override
        public void hardDeleteCategory(Integer id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            categoryRepository.delete(category);
        }

        @Override
        public CategoryResponse softDeleteCategory(Integer id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            category.setIsDelete(true);
            return categoryMapper.mapCategoryToCategoryResponse(categoryRepository.save(category));
        }

//     

//        @Override
//        public CategoryResponse updateCategory(Integer id, UpdateCategory request) {
//            Category category = categoryRepository.findById(id)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
//
//            categoryMapper.(request, category);
//            return categoryMapper.mapCategoryToCategoryResponse(categoryRepository.save(category));
//        }


//        @Override
//        public Page<CategoryResponse> getAllCategory(int page, int size) {
//            Pageable pageable = PageRequest.of(page, size);
//
//
//    //        List<Category> categories = categoryRepository.findAll();
//    //
//    //        return categories.stream()
//    //                .map(category -> new CategoryResponse(
//    //                        category.getId(),
//    //                        category.getName(),
//    //                        category.getDescription(),
//    //                        category.getIcon(),
//    //                        category.getIsDelete(),
//    //                        category.getParentCategory()
//    //                ))
//    //                .toList();
//            return null;
//        }
    }
