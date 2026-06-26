package co.istad.bidin.ecommerceite.features.order.mapper;

import co.istad.bidin.ecommerceite.features.order.Order;
import co.istad.bidin.ecommerceite.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapOrderToOrderResponse(Order order);
}
