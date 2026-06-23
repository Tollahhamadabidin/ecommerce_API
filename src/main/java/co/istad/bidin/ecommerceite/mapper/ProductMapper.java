package co.istad.bidin.ecommerceite.mapper;

import co.istad.bidin.ecommerceite.domain.Product;
import co.istad.bidin.ecommerceite.dto.ProductRequest;
import co.istad.bidin.ecommerceite.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapProductRequestToProduct(ProductRequest productRequest);
    ProductResponse mapProductToProductResponse(Product product);
}
