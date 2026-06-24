package co.istad.bidin.ecommerceite.features.product;

import co.istad.bidin.ecommerceite.features.category.Category;
import co.istad.bidin.ecommerceite.features.product.dto.ProductRequest;
import co.istad.bidin.ecommerceite.features.product.dto.ProductResponse;
import co.istad.bidin.ecommerceite.features.category.CategoryRepository;
import co.istad.bidin.ecommerceite.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
//        validate productName
        if (productRepository.existsByName(productRequest.name())){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT, "Product name has already been used");
        }

//        validate category ID
       Category category = categoryRepository.findById(productRequest.categoryId())
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category has not been found"));

//        transfer data from DTO to Mapper

        Product product = productMapper.mapProductRequestToProduct(productRequest);

        product.setCategory(category);
        product.setCode(GenerateUtils.generateProductCode());// ITE-3RD-1234
        product.setSlug(GenerateUtils.generateSlug(productRequest.name()));
        product.setIsDelete(false);
        product.setIsAvailable(true);

        Product saveProduct = productRepository.save(product);
        return productMapper.mapProductToProductResponse(saveProduct);
    }

    @Override
    public Page<ProductResponse> getAllProduct(
            int pageNumber,
            int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Product> products = productRepository.findAll(pageable);
       return products.map(productMapper::mapProductToProductResponse);
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "product id not found"));
        return productMapper.mapProductToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
//        validate ID
        if (id == 0 || id <= 0 ){
            throw new ResponseStatusException(HttpStatus
                    .BAD_REQUEST, "Invalid Product id");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "Product id not found"));
//check code duplicate
        if(!product.getCode().equals(productRequest.name()) && productRepository.existsByName(productRequest.name())){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT, "Product code is already exite");
        }

//

//        find category
        Category category = categoryRepository
                .findById(productRequest.categoryId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

//        update fields

        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setThumbnail(productRequest.thumbnail());
        product.setUnitPrice(productRequest.unitPrice());
        product.setQty(productRequest.qty());
        product.setCategory(category);
        product.setIsDelete(false);
        product.setIsAvailable(true);

        Product productUpdate = productRepository.save(product);

        return productMapper.mapProductToProductResponse(productUpdate);
    }

    @Override
    public void softDelete(Integer id) {
        Product softDeleteProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Product not found"));

        softDeleteProduct.setIsDelete(true);
        productRepository.save(softDeleteProduct);

    }

    @Override
    public void hardDelete(Integer id) {
        Product hardDeleteProduct = productRepository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Product not found"));
        productRepository.delete(hardDeleteProduct);
    }
}
