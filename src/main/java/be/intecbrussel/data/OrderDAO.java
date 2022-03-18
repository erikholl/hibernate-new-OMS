package be.intecbrussel.data;

import be.intecbrussel.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDAO {
    EntityManager em = EntityManagerProvider.getEntityManager();

    public void saveOrder(Order o) {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
//        em.close(); TODO: move close outside of local methods
    }

    public List<Order> readOrder(String orderNr) {
        String jpql =
                "SELECT o FROM Order o WHERE o.orderNr LIKE '" + orderNr +"'";
        // TODO: make secure (https://www.baeldung.com/jpa-query-parameters)
        TypedQuery<Order> tq = em.createQuery(jpql, Order.class);
        return tq.getResultList();
    }
}
