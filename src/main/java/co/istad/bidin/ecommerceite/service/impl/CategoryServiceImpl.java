    package co.istad.bidin.ecommerceite.service.impl;

    import co.istad.bidin.ecommerceite.domain.Category;
    import co.istad.bidin.ecommerceite.dto.CategoryResponse;
    import co.istad.bidin.ecommerceite.dto.CreateCategoryRequest;
    import co.istad.bidin.ecommerceite.mapper.CategoryMapper;
    import co.istad.bidin.ecommerceite.repository.CategoryRepository;
    import co.istad.bidin.ecommerceite.service.CategoryService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    @Service
    @RequiredArgsConstructor
    public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;
        private final CategoryMapper categoryMapper;


        public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {

            if (categoryRepository.existsByName(createCategoryRequest.name())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Category already exists"
                );
            }

            Category parentCategory = null;

            if (createCategoryRequest.parentCategoryId() != null) {
                parentCategory = categoryRepository
                        .findById(createCategoryRequest.parentCategoryId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Parent category not found"
                        ));
            }

            Category category = categoryMapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
            category.setParentCategory(parentCategory);
            category.setIsDelete(false);

            return categoryMapper.mapCategoryToCategoryResponse(categoryRepository.save(category));
        }


        @Override
        public Page<CategoryResponse> getAllCategory(int pageNUmber, int pageSize) {
            Pageable pageable = PageRequest.of(pageNUmber, pageSize);
            return categoryRepository.findAll(pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }

        @Override
        public CategoryResponse getCategoryById(Integer id) {
            Category getById = categoryRepository.findById(id)
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Id not found"));
            return categoryMapper.mapCategoryToCategoryResponse(getById);
        }

        @Override
        public Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int pageNumber, int pageSize) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            categoryRepository.findById(parentId)
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent Category not found"));
            return categoryRepository.findAllByParentCategoryId(parentId,pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }

        @Override
        public void softDeleteCategory(Integer id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
            category.setIsDelete(true);
            List<Category> subCategory = categoryRepository.findAllByParentCategoryId(id);
            subCategory.forEach(child->child.setIsDelete(true));
            categoryRepository.save(category);
            categoryRepository.saveAll(subCategory);
        }

        @Override
        public void hardDeleteCategory(Integer id) {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Category not found"));

            List<Category> subCategories= categoryRepository.findAllByParentCategoryId(id);
            categoryRepository.deleteAll(subCategories);
            categoryRepository.deleteById(id);
        }

        @Override
        public CategoryResponse updateCategoryById(Integer id, CreateCategoryRequest categoryRequest) {
            // Validate ID
            if (id == null || id <= 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid category id"
                );
            }

            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Category not found"
                    ));

            if (Boolean.TRUE.equals(category.getIsDelete())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Cannot update deleted category"
                );
            }

             // Validate duplicate name
            String newName = categoryRequest.name();

            if (!category.getName().equalsIgnoreCase(newName)
                    && categoryRepository.existsByName(newName)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Category name already exists"
                );
            }

            category.setName(newName);
            category.setDescription(categoryRequest.description());
            category.setIcon(categoryRequest.icon());

            Category updated = categoryRepository.save(category);

            return categoryMapper.mapCategoryToCategoryResponse(updated);
        }
    }
