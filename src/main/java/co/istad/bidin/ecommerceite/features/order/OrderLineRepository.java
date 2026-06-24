package co.istad.bidin.ecommerceite.features.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer   > {
}
