package co.istad.bidin.ecommerceite.repository;

import co.istad.bidin.ecommerceite.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OderRepository extends JpaRepository<Order, UUID> {
}
