package co.istad.bidin.ecommerceite.features.product;

import co.istad.bidin.ecommerceite.features.product.dto.ProductRequest;
import co.istad.bidin.ecommerceite.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapProductRequestToProduct(ProductRequest productRequest);

    ProductResponse mapProductToProductResponse(Product product);
}
