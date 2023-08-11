package repository.impl;

import domain.enums.ProductType;
import domain.models.Product;
import mapping.dtos.ProductDto;
import mapping.mappers.ProductMapper;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductRepositoryimpl implements ProductRepository {
    private List<Product> products;
    private List<Product> productsA;
    private List<Product> productsB;
    private List<List<Product>> productsTotal;
    private List<Product> productsUnited;

    public List<Product> getProductsUnited() {
        return productsUnited;
    }

    public void setProductsUnited(List<Product> productsUnited) {
        this.productsUnited = productsUnited;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductRepositoryimpl() {

        Product product1 = new Product(1, "Car", ProductType.TOYS, 120000.0);
        Product product2 = new Product(2, "Book2", ProductType.BOOKS, 100000.0);
        Product product3 = new Product(3, "Sheets", ProductType.BABIES, 100000.0);
        Product product4 = new Product(4, "Book1", ProductType.BOOKS, 150000.0);

        products = new ArrayList<Product>();

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        productsA = new ArrayList<Product>();
        productsA.add(product1);
        productsA.add(product2);

        productsB = new ArrayList<Product>();
        productsB.add(product1);
        productsB.add(product3);
        productsB.add(product4);

        productsTotal = Arrays.asList(productsA, productsB);

        productsUnited = productsTotal.stream()
                .flatMap(list -> list.stream())
                .collect(toList());
    }

    @Override
    public List<ProductDto> listAllProduct() {
        return ProductMapper.mapFrom(products);
    }

    @Override
    public List<ProductDto> listAllProductsA() {
        return ProductMapper.mapFrom(productsA);
    }

    @Override
    public List<ProductDto> listAllProductsB() {
        return ProductMapper.mapFrom(productsB);
    }

    @Override
    public List<ProductDto> listAllProductsUnited() {
        return ProductMapper.mapFrom(productsUnited);
    }

}
