package co.istad.bidin.ecommerceite.features.order;

import co.istad.bidin.ecommerceite.features.order.dto.CreateOrderRequest;
import co.istad.bidin.ecommerceite.features.order.dto.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {
   OrderResponse createNew(CreateOrderRequest createOrderRequest);

   Page<OrderResponse> findAll (int pageNumber, int pageSize);

   OrderResponse findById (UUID id);

   void softDelete (UUID id);

   void hardDelete (UUID id);

   OrderResponse paymentById (UUID id);
}
