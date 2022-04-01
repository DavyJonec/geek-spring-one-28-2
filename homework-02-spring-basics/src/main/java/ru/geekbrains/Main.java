package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scanner = new Scanner(System.in);

        CartService cartService = null;
        while (true) {
            System.out.print("Enter command: ");
            String cmd = scanner.nextLine().trim().toUpperCase();
            switch (cmd) {
                case "NEW":
                    cartService = context.getBean(CartService.class);
                    System.out.println("Cart created");
                    break;
                case "ADD PRODUCT":
                    if (cartService == null) {
                        System.out.println("Please create a new Cart");
                        break;
                    }
                    System.out.print("Enter id: ");
                    long id = scanner.nextLong();
                    System.out.print("Enter count: ");
                    int count = scanner.nextInt();
                    cartService.addProduct(id, count);
                    break;
                case "SHOW":
                    if (cartService == null) {
                        System.out.println("Please create a new Cart");
                        break;
                    }
                    cartService.getAll().forEach(System.out::println);
                    break;
                case "EXIT":
                    return;
            }
        }

    }
}
