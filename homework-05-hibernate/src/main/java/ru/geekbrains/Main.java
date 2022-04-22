package ru.geekbrains;


import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//        List<Product> products = em.createNamedQuery("findAllProducts", Product.class).getResultList();


        //Insert
//        em.getTransaction().begin();
//
//        em.persist(new Product("Product 1", 2345674));
//        em.persist(new Product("Product 22", 1234));
//        em.persist(new Product("Product 33", 21));
//
//        em.getTransaction().commit();

        //Select
//        Product product = em.find(Product.class, 1L);
//        System.out.println("Product with in 1 " + product);

        //Select HQL/JPQL hibernate query language / java persistent query language
//        List<Product> products = em.createQuery("select p from Product p where p.id in (1, 2)", Product.class)
//                .getResultList();
//        products.forEach(System.out::println);

        //Update 1
//        em.getTransaction().begin();
//        Product product = em.find(Product.class, 1L);
//        product.setTitle("Product 22222");
//
//        em.getTransaction().commit();

        //Update 2
//        em.getTransaction().begin();
//        Product product = new Product("Product 1234", 666);
//        product.setId(2L);
//        em.merge(product);
//        product.setTitle("Product 22222");
//
//        em.getTransaction().commit();

        //Delete
//        em.getTransaction().begin();
//
//        /* With select
//        Product product = em.find(Product.class, 1L);
//        em.remove(product);*/
//
//        em.createQuery("delete from Product p where p.id = 2")
//                        .executeUpdate();
//
//        em.getTransaction().commit();

    /*
        em.close();

        emFactory.close();
        */

    }
}
