package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.Category;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
    boolean existsByName(String name);
    Page<Category> findAllByParentCategoryId(Integer parentId, Pageable pageable);
    List<Category> findAllByParentCategoryId(Integer parentId);
}
