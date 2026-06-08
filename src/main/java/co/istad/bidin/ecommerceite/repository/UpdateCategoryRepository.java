package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateCategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    Page<Category> findAll(Pageable pageable);
    List<Category> findByParentCategoryId(Integer parentCategoryId);
}
