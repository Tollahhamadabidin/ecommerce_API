package co.istad.bidin.ecommerceite.service;

import co.istad.bidin.ecommerceite.dto.ProductRequest;
import co.istad.bidin.ecommerceite.dto.ProductResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

//    getAllProduct
    Page<ProductResponse> getAllProduct (int pageNumber, int pageSize);

//    getProductById
    ProductResponse getProductById(Integer id);

    ProductResponse updateProduct (Integer id, ProductRequest productRequest);

//    soft delete
    void softDelete(Integer id);

//    hard delete
    void  hardDelete (Integer id);
}
