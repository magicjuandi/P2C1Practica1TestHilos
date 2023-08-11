package services.impl;

import domain.models.Client;
import domain.models.Order;
import domain.models.Product;
import services.OrderService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.enums.ClientType.LEVEL2;

public class OrderServiceimpl implements OrderService {
    @Override
    public List<Product> getSizeOrders(List<Order> orders, LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(p -> p.getClient().getLevel().equals(LEVEL2))
                .filter(p -> p.getOrderDate().isBefore(endDate))
                .filter(p -> p.getOrderDate().isAfter(startDate))
                .flatMap(p -> p.getProducts().stream())
                .distinct()
                .toList();
    }

    @Override
    public List<Order> getLastOrders(List<Order> orders, LocalDate endDate) {
        return orders.stream()
                .filter(p -> p.getOrderDate().isBefore(endDate))
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();
    }

    @Override
    public double totalPrice(List<Order> orders) {
        return orders.stream()
                .filter(p -> p.getOrderDate().equals(LocalDate.of(2023,01,10)))
                .flatMap(p -> p.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .sum();
    }

    @Override
    public double totalPriceProm(List<Order> orders) {
        return orders.stream()
                .filter(p -> p.getOrderDate().equals(LocalDate.of(2023,01,10)))
                .flatMap(p -> p.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<Client, List<Order>> sortByClient(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getClient,
                        HashMap::new,
                        Collectors.toList()));
    }

    @Override
    public Long countOrder(List<Order> orders) {
        return orders.stream()
                .filter(p -> p.getOrderDate().equals(LocalDate.of(2023,01,10)))
                .flatMap(p -> p.getProducts().stream())
                .mapToLong(p -> p.getId())
                .count();
    }
}
