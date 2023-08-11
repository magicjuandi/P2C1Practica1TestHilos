package services.impl;

import domain.enums.ProductType;
import domain.models.Product;
import org.junit.jupiter.api.Test;
import repository.impl.ProductRepositoryimpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceimplTest {

    @Test
    void listByPriceByCategory(){
        ProductServiceimpl operation = new ProductServiceimpl();
        ProductRepositoryimpl pRep = new ProductRepositoryimpl();
        List<Product> products = pRep.getProducts();
        List<Product> expected = new ArrayList<>();
        expected.add(pRep.getProducts().get(1));
        expected.add(pRep.getProducts().get(3));
        List<Product> result = operation.listByPriceByCategory(products, ProductType.BOOKS);
        assertEquals(expected, result,"La Operacion no trabajo de manera correcta");
    }

    @Test
    void listByCategoryTotal() {
    }

    @Test
    void listToys() {
    }

    @Test
    void listCheapProduct() {
    }

    @Test
    void listExpensiveProduct() {
    }
}