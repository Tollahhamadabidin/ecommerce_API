package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
    boolean existsByName(String name);
}
