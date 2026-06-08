package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer   > {
}
