package co.istad.bidin.ecommerceite.features.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);

    boolean existsBySlug(String slug);
}
