package be.intecbrussel.service;

import be.intecbrussel.data.OrderDAO;
import be.intecbrussel.model.Address;
import be.intecbrussel.model.Client;
import be.intecbrussel.model.Order;
import be.intecbrussel.model.Product;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public void saveOrder(Client newClient, Address newAddress, List<Product> pList) {
        Order orderToSave = new Order();

        // complete product-list with FK order id
        for (Product p : pList) {
            p.setProductOrder(orderToSave);
        }

        orderToSave.setClient(findClient(newClient));
        orderToSave.setDelivery_address(findAddress(newAddress));

        orderToSave.setOrderProductList(pList)
                .setOrderNr(orderNrGenerator())
                .setSent(false)
                .setVatFree(false) // TODO: implement country table
                .setOrderDate(Date.valueOf(LocalDate.now()));
        orderDAO.saveOrder(orderToSave);
    }

    public void readOrder(String orderNr) {
        List<Order> orderList = orderDAO.readOrder(orderNr);

        for (Order o : orderList) {
            System.out.println(o);
            System.out.println(o.getClient());
            System.out.println(o.getDelivery_address());
            List<Product> productList = o.getOrderProductList();
            for (Product p : productList) {
                System.out.println(p);
            }
        }
    }

    public void findClientByEmailAndPw(String email, String pw) {
        Client verifiedClient;
        try {
            verifiedClient = orderDAO.findClientByEmailAndPw(email, pw);
            System.out.println(verifiedClient);
        } catch (NoResultException nre) {
            System.out.println("username and/or password incorrect - client " +
                                       "not found!");
        }
    }

    private Client findClient(Client newClient) {
        Client existingClient = null;
        try {
            existingClient = orderDAO.findClient(newClient);
        } catch (NoResultException nre) {
            System.out.println("client did not yet exist in DB >> new client " +
                                       "record created!");
        }
        return existingClient == null ? newClient : existingClient;
    }

    private Address findAddress(Address newAddress) {
        Address existingAddress = null;
        try {
            existingAddress = orderDAO.findAddress(newAddress);
        } catch (NoResultException nre) {
            System.out.println("address did not yet exist in DB >> new " +
                                       "address record created!");
        }
        return existingAddress == null ? newAddress : existingAddress;
    }

    private String orderNrGenerator() {
        StringBuilder sb = new StringBuilder("ORD-");
        Order lastOrder;

        try {
            lastOrder = orderDAO.lastOrder();
        } catch (NoResultException nre) {
            // if arriving in catch, init orderNr is needed: ORD-yyyyMM-0001
            OrderNumberHelper helper = new OrderNumberHelper();
            return "ORD-" + helper.orderNrDatePrefix() + "-0001";
        }
        // if lastOrder is successfully created, catch is skipped and below
        // lines are executed
        OrderNumberHelper helper = new OrderNumberHelper();
        helper.setLastOrderDate(lastOrder.getOrderDate());
        helper.setLastOrderNrSeq(lastOrder.getOrderNr().substring(11));

        return helper.generateOrderNr();
    }
}
