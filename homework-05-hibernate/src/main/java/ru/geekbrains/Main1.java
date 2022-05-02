package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main1 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();


//INSERT
//        Product product = em.find(Product.class, 3L);
//        em.getTransaction().begin();
//        em.persist(new Contact(Contact.ContactType.HOME_PHONE, "1223434", product));
//        em.getTransaction().commit();


//        em.getTransaction().begin();
//        Product product = new Product("Potato", 2354);
//        product.getContacts().add(new Contact(Contact.ContactType.HOME_PHONE, "55522222", product));
//        product.getContacts().add(new Contact(Contact.ContactType.WORK_PHONE, "5553235435", product));
//        em.persist(product);
//        em.getTransaction().commit();

        //SELECT
//        List<Product> products = em.createQuery(
//                "select  distinct p" +
//                        "from Product p" +
//                        "inner join fetch p.contacts c", Product.class)
//                .getResultList();
//
//        for (Product product : products) {
//            product.getContacts().forEach(System.out::println);
//        }


        //DELETE
//        em.getTransaction().begin();
//
//        Product product = em.find(Product.class, 3L);
//
//        product.getContacts().remove(0);
//        em.merge(product);
//
//        em.getTransaction().commit();

        em.close();
    }
}
