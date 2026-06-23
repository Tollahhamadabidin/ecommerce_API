package co.istad.bidin.ecommerceite.service.impl;

import co.istad.bidin.ecommerceite.domain.Category;
import co.istad.bidin.ecommerceite.domain.Product;
import co.istad.bidin.ecommerceite.dto.ProductRequest;
import co.istad.bidin.ecommerceite.dto.ProductResponse;
import co.istad.bidin.ecommerceite.mapper.ProductMapper;
import co.istad.bidin.ecommerceite.repository.CategoryRepository;
import co.istad.bidin.ecommerceite.repository.ProductRepository;
import co.istad.bidin.ecommerceite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.existsByCode(productRequest.code())){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT, "Code is already exite");
        }
        if (productRepository.existsBySlug(productRequest.slug())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Slug is already exite");
        }
       Category category = categoryRepository.findById(productRequest.categoryId())
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not found"));

        Product product = productMapper.mapProductRequestToProduct(productRequest);

        product.setIsDelete(false);
        product.setIsAvailable(true);

        Product saveProduct = productRepository.save(product);
        return productMapper.mapProductToProductResponse(saveProduct);

    }

    @Override
    public Page<ProductResponse> getAllProduct(
            int pageNumber,
            int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable)
                .map(productMapper::mapProductToProductResponse);
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
        if(!product.getCode().equals(productRequest.code()) && productRepository.existsByCode(productRequest.code())){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT, "Product code is already exite");
        }

//        check slug duplicate
        if (!product.getSlug().equals(productRequest.slug())
                && productRepository.existsBySlug(productRequest.slug())){
            throw new ResponseStatusException
                    (HttpStatus.CONFLICT, "Slug has already exite");
        }

//        find category
        Category category = categoryRepository
                .findById(productRequest.categoryId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

//        update fields
        product.setCode(productRequest.code());
        product.setSlug(productRequest.slug());
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
