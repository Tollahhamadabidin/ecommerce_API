package co.istad.bidin.ecommerceite.features.product;

import co.istad.bidin.ecommerceite.features.product.dto.ProductRequest;
import co.istad.bidin.ecommerceite.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);


    Page<ProductResponse> getAllProduct (int pageNumber, int pageSize);

//    getProductById
    ProductResponse getProductById(Integer id);

    ProductResponse updateProduct (Integer id, ProductRequest productRequest);

//    soft delete
    void softDelete(Integer id);

//    hard delete
    void  hardDelete (Integer id);
}
