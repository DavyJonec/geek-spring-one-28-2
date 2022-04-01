package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.insert(new Product("Potato", 22));
        productRepository.insert(new Product("Banana", 35));
        productRepository.insert(new Product("Duck", 87));
        productRepository.insert(new Product("Lamb", 124));
        productRepository.insert(new Product("Chicken", 453));


        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
