package ru.geekbrains;

import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Product> findById(Long id) {
        return executeForEntityManager(em ->
                Optional.ofNullable(em.find(Product.class, id)));
    }

    public List<Product> findAll() {
        return executeForEntityManager(em ->
                em.createNamedQuery("findAllProducts", Product.class).getResultList());
    }

    public void saveOrUpdate(Product product) {
        executeInTransaction(em -> {
            if (product.getId() != null) {
                em.persist(product);
                return;
            } else {
                em.merge(product);
            }
            em.getTransaction().commit();
        });
    }

    public void delete(long id){
        executeInTransaction(em -> em.createNamedQuery("deleteProduct")
                .setParameter("id", id)
                .executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> func) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return func.apply(em);
        } finally {
            emFactory.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
