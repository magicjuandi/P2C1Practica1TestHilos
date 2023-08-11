package repository.impl;

import domain.enums.OrderType;
import domain.models.Client;
import domain.models.Order;
import domain.models.Product;
import mapping.dtos.OrderDto;
import mapping.mappers.ClientMapper;
import mapping.mappers.OrderMapper;
import mapping.mappers.ProductMapper;
import repository.ClientRepository;
import repository.OrderRepository;
import repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryimpl implements OrderRepository {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    List<Product> products;
    List<Product> productsA;
    List<Product> productsB;
    List<Client> clients;
    public OrderRepositoryimpl() {

        ClientRepository clientRepository = new ClientRepositoryimpl();
        ProductRepository productRepository = new ProductRepositoryimpl();
        productsA = ProductMapper.mapFromDto(productRepository.listAllProductsA());
        productsB = ProductMapper.mapFromDto(productRepository.listAllProductsB());

        clients = ClientMapper.mapFromDto(clientRepository.listAllClient());


        Order order1 = new Order(1L,
                OrderType.DELIVERED,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 03, 24),
                productsA,
                clients.get(0));
        Order order2 = new Order(2L,
                OrderType.PENDING,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsB,
                clients.get(1));
        Order order3 = new Order(3L,
                OrderType.PENDING,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsB,
                clients.get(0));

        orders = new ArrayList<Order>();

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Override
    public List<OrderDto> listAllOrder() {
        return OrderMapper.mapFrom(orders);
    }
}
