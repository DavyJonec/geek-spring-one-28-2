package ru.geekbrains.commands;

import org.springframework.stereotype.Component;
import ru.geekbrains.CartService;

import java.util.Scanner;

@Component
public class CartAddCommand implements Command{

    @Override
    public String getName() {
        return "ADD";
    }

    @Override
    public CartService execute(Scanner scn, CartService cart) {
        if (cart == null) {
            System.out.println("Please create new Cart");
            return cart;
        }
        System.out.println("Enter id: ");
        long id = scn.nextLong();
        System.out.println("Enter count: ");
        int count = scn.nextInt();
        cart.addProduct(id, count);
        return cart;
    }
}
