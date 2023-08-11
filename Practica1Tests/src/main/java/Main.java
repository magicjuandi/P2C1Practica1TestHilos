import domain.enums.ProductType;
import domain.models.Order;
import domain.models.Product;
import repository.impl.OrderRepositoryimpl;
import repository.impl.ProductRepositoryimpl;
import services.impl.OrderServiceimpl;
import services.impl.ProductServiceimpl;
import services.impl.ThreadsServiceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {


        String option = "0";
        ProductRepositoryimpl pRep = new ProductRepositoryimpl();
        OrderRepositoryimpl oRep = new OrderRepositoryimpl();
        ProductServiceimpl pSer = new ProductServiceimpl();
        OrderServiceimpl oSer = new OrderServiceimpl();
        ThreadsServiceimpl tSer = new ThreadsServiceimpl();


        List<Product> products = pRep.getProducts();
        List<Product> productsUnited = pRep.getProductsUnited();
        List<Order> orders = oRep.getOrders();


        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n Menu" +
                    "\n1. List of Products (books) higher than 100.000" +
                    "\n2. List of Products (babies)" +
                    "\n3. List of Products (toys) with discount" +
                    "\n4. List of Products ordered by client Level 2" +
                    "\n5. List of chepeast Products (books)" +
                    "\n6. List of latest Orders" +
                    "\n7. Total price of orders between dates" +
                    "\n8. Average price of order between dates" +
                    "\n9. Orders grouped by" +
                    "\n10. Most expensive product" +
                    "\n11. Exit");

            option = scan.next();

            switch (option) {
                case "1": {
                    CompletableFuture.runAsync(() -> {//1 with runAsync (simulation).
                        tSer.slowProcess();
                    });
                    tSer.sleeper(6000);
                    System.out.println(pSer.listByPriceByCategory(products, ProductType.BOOKS));
                    break;
                }
                case "2": {
                    System.out.println(pSer.listByCategoryTotal(productsUnited, ProductType.BABIES));//2
                    break;
                }
                case "3": {
                    System.out.println(pSer.listToys(products, ProductType.TOYS));//3
                    break;
                }
                case "4": {
                    System.out.println(oSer.getSizeOrders(orders,
                            LocalDate.of(2023, 01, 02),
                            LocalDate.of(2023, 02, 23)));//4
                    break;
                }
                case "5": {
                    System.out.println(pSer.listCheapProduct(products, ProductType.BOOKS));//5
                    break;
                }
                case "6": {
                    System.out.println(oSer.getLastOrders(orders, LocalDate.now()));//6
                    break;
                }
                case "7": {
                    CompletableFuture future = CompletableFuture.supplyAsync(()->{//7 with exceptionally and thenAccept.
                        throw new RuntimeException("Data may have been modified");
                    });
                    CompletableFuture future2 = future.exceptionally(error -> {
                        System.out.println("Exception: " + error.toString());
                        return 378;
                    });
                    future2.thenAccept(result -> {
                        System.out.println("Error: " + result + " (Data is recovering...)");
                    });
                    tSer.sleeper(5000);
                    System.out.println("The total is: " + oSer.totalPrice(orders));
                    break;
                }
                case "8": {
                    CompletableFuture.supplyAsync(() -> {//8 with supplyAsync, thenApply, thenAccept and thenRun.
                        return oSer.totalPrice(orders);
                    }).thenApply((value) -> {
                        return value / oSer.countOrder(orders);
                    }).thenAccept((value) -> {
                        System.out.println("Average with Threads: " + value);
                    }).thenRun(() -> {
                        System.out.println("Avarge with the stream method: " +oSer.totalPriceProm(orders));
                    });
                    tSer.sleeper(5000);
                    break;
                }
                case "9": {
                    System.out.println(oSer.sortByClient(orders));//9
                    break;
                }
                case "10": {
                    System.out.println(pSer.listExpensiveProduct(productsUnited));//10
                    break;
                }
                case "11": {
                    option = "11";
                    break;
                }
            }
        } while (!option.equals("11"));
    }
}
