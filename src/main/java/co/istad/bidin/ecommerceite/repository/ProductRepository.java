package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCode(String code);

    boolean existsBySlug(String slug);
}
