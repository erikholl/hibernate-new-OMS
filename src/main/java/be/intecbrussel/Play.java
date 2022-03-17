package be.intecbrussel;

import be.intecbrussel.data.EntityManagerProvider;
import be.intecbrussel.model.Address;
import be.intecbrussel.model.Client;
//import be.intecbrussel.model.Address;
import be.intecbrussel.model.Order;
import be.intecbrussel.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Play {
    public static void main(String[] args) {
        // client - order - products
        // client can have multiple addresses
        // address can have multiple clients

        // create order - set attributes like client, address, products
        // 1 client can have multiple orders - OneToMany / ManyToOne
        // 1 order can have 1 client only - OneToOne
        // 1 client can have multiple addresses - 1 address can have multiple
        // clients >> ManyToMany
        // 1 order can have multiple productLINES - 1 productLINE can belong
        // to 1 order only >> OneToMany / ManyToOne

        // classes needed: Client, Address, Order, Product
        EntityManager em = EntityManagerProvider.getEntityManager();

        Client newClient = new Client();
        newClient.setFirstName("Jan");
        newClient.setLastName("Jansen");
        newClient.setEmail("janjansen@gmail.com");
        newClient.setPassword("jansen13");

        Address newAddress = new Address();
        newAddress.setStreetName("Struikgewas");
        newAddress.setHouseNr(3);
        newAddress.setZipCode(1111);
        newAddress.setCityName("Haarlem");

        Order newOrder = new Order();
        newOrder.setClient(newClient);
        newOrder.setOrderNr("ORD-202203-0001");
        newOrder.setSent(false);
        newOrder.setVatFree(false);
        newOrder.setOrderDate(Date.valueOf(LocalDate.now()));
        newOrder.setDelivery_address(newAddress);

        Product newP1 = new Product();
        newP1.setProductOrder(newOrder);
        newP1.setProductName("lightbulb");
        newP1.setAmount(200);
        newP1.setPricePerUnit(new BigDecimal(1.5));

        Product newP2 = new Product();
        newP2.setProductOrder(newOrder);
        newP2.setProductName("roll of wire");
        newP2.setAmount(55);
        newP2.setPricePerUnit(new BigDecimal(0.8));

        em.getTransaction().begin();
        em.persist(newClient);
        em.persist(newAddress);
        em.persist(newOrder);
        em.persist(newP1);
        em.persist(newP2);
        em.getTransaction().commit();
        em.close();

    }
}
