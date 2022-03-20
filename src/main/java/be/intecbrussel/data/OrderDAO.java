package be.intecbrussel.data;

import be.intecbrussel.model.Address;
import be.intecbrussel.model.Client;
import be.intecbrussel.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    // TODO: throw (and handle) exception
    public List<Order> readOrder(String orderNr) {
        String jpql = "SELECT o FROM Order o WHERE o.orderNr = :orderNr";
        TypedQuery<Order> tq = em.createQuery(jpql, Order.class);
        tq.setParameter("orderNr", orderNr);
        return tq.getResultList();
    }

    // TODO: throw (and handle) exception
    public Client findClientByEmail(String email) {
        String jpql = "SELECT c FROM Client c WHERE c.email =:email";
        TypedQuery<Client> tq = em.createQuery(jpql, Client.class);
        tq.setParameter("email", email);
        return tq.getSingleResult();
    }

    // TODO: method >> provide order details with input Client
    // TODO: method >> update order sent status
    // TODO: method >> update order product details (only when status not sent)
    // TODO: method >> update address existing client

    public Client findClientByEmailAndPw(String email, String password) throws NoResultException {
        String jpql = "SELECT c FROM Client c WHERE c.email =:email AND c" +
                ".password =:password";
        TypedQuery<Client> tq = em.createQuery(jpql, Client.class);
        tq.setParameter("email", email);
        tq.setParameter("password", password);
        return tq.getSingleResult();
    }

    public Client findClient(Client newClient) throws NoResultException {
        String firstName = newClient.getFirstName();
        String lastName = newClient.getLastName();
        String email = newClient.getEmail();
        String jpql =
                "SELECT c FROM Client c WHERE c.firstName = '" + firstName +
                        "' AND c.lastName = '" + lastName + "' AND c.email = " +
                        "'" + email + "'";
        TypedQuery<Client> tq = em.createQuery(jpql, Client.class);
        return tq.getSingleResult();
    }

    public Address findAddress(Address newAddress) throws NoResultException {
        String street = newAddress.getStreetName();
        int houseNr = newAddress.getHouseNr();
        String houseNrSub = newAddress.getHouseNrSub();
        int zipCode = newAddress.getZipCode();
        String city = newAddress.getCityName();
        String jpql =
                "SELECT a FROM Address a WHERE a.streetName = '" + street +
                        "' AND a.houseNr = '" + houseNr + "' AND a.houseNrSub" +
                        " = '" + houseNrSub + "' AND a.zipCode = '" + zipCode + "' AND a.cityName = '" + city + "'";
        TypedQuery<Address> tq = em.createQuery(jpql, Address.class);
        return tq.getSingleResult();
    }

    // alternative read
//    public Order readOrder2(int id) {
//        return em.find(Order.class, id);
//    }
}
