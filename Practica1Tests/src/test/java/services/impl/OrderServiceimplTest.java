package services.impl;

import domain.enums.ProductType;
import domain.models.Order;
import domain.models.Product;
import repository.impl.OrderRepositoryimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceimplTest {

    @org.junit.jupiter.api.Test
    void getSizeOrders() {
    }

    @org.junit.jupiter.api.Test
    void getLastOrders() {
        OrderServiceimpl operation = new OrderServiceimpl();
        OrderRepositoryimpl oRep = new OrderRepositoryimpl();
        List<Order> orders = oRep.getOrders();
        List<Order> expected = new ArrayList<Order>();
        expected.add(oRep.getOrders().get(0));
        expected.add(oRep.getOrders().get(1));
        expected.add(oRep.getOrders().get(2));
        List<Order> result = operation.getLastOrders(orders, LocalDate.now());
        assertEquals(expected, result,"La Operacion no trabajo de manera correcta");

    }

    @org.junit.jupiter.api.Test
    void totalPrice() {
        OrderServiceimpl operation = new OrderServiceimpl();
        OrderRepositoryimpl oRep = new OrderRepositoryimpl();
        List<Order> orders = oRep.getOrders();
        Double expected = 740000.0;
        Double result = operation.totalPrice(orders);
        assertEquals(expected, result,"La Operacion no trabajo de manera correcta");

    }

    @org.junit.jupiter.api.Test
    void totalPriceProm() {
    }

    @org.junit.jupiter.api.Test
    void sortByClient() {

    }
}