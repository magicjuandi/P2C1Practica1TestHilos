package repository;

import domain.models.Order;
import mapping.dtos.OrderDto;

import java.util.List;

public interface OrderRepository {
    List<OrderDto> listAllOrder();
}
