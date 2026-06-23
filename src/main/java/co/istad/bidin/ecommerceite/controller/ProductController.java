package co.istad.bidin.ecommerceite.controller;

import co.istad.bidin.ecommerceite.dto.ProductRequest;
import co.istad.bidin.ecommerceite.dto.ProductResponse;
import co.istad.bidin.ecommerceite.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<ProductResponse> getAllProduct(
            @RequestParam (defaultValue = "0") int pageNumber,
            @RequestParam (defaultValue = "25") int pageSize
    ){
        return productService.getAllProduct(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ProductResponse updateProduct (@PathVariable Integer id, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id, productRequest);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/soft-delete")
    public void productSoftDelete (@PathVariable Integer id){
        productService.softDelete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void productHardDelete(@PathVariable Integer id){
        productService.hardDelete(id);
    }
}
