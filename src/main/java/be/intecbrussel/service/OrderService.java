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

    public void getOrderByOrderNr(String orderNr) {
        Order order = findSingleOrder(orderNr);
        if (order != null) { printOrderDetails(order); }
    }   // if null, exception already handled in findSingleOrder

    public void getOrdersNotSent() {
        try {
            List<Order> orderList = orderDAO.findOrdersNotSent();
            for (Order o : orderList) {
                System.out.println("Order with nr " + o.getOrderNr() + " is " +
                                           "not yet sent. Details of the order:");
                printOrderDetails(o);
                System.out.println("- - - - - -");
            }
        } catch (NoResultException nre) {
            System.out.println("all orders are sent!");
        }
    }

    public void updateOrderNotSentToSent(String orderNr) {
        Order orderToUpdate = findSingleOrder(orderNr);

        if (orderToUpdate != null) {
            System.out.println(
                    "Order details prior to update: " + orderToUpdate);
            orderToUpdate.setSent(true);
            System.out.println(
                    "Order details after update: "
                            + orderDAO.updateOrderNotSentToSent(orderToUpdate).toString());
        }
    }

    public void getClientByEmailAndPw(String email, String password) {
        Client existingClient = findClientByEmailAndPw(email, password);

        if (existingClient != null) {
            printClientDetails(existingClient);
        }
    }

    public void getClientByEmail(String email) {
        Client existingClient = findClientByEmail(email);

        if (existingClient != null) {
            printClientDetails(existingClient);
        }

    }
    private Client findClientByEmail(String email) {
        Client existingClient = null;
        try {
            existingClient = orderDAO.findClientByEmail(email);
        } catch (NoResultException nre) {
            System.out.println("client with email " + email + " not found.");
        }
        return existingClient;
    }

    private Client findClientByEmailAndPw(String email, String pw) {
        Client existingClient = null;

        try {
            existingClient = orderDAO.findClientByEmailAndPw(email, pw);
        } catch (NoResultException nre) {
            System.out.println(
                    "email and/or password incorrect - client not found!");
        }
        return existingClient;
    }

    private Order findSingleOrder(String orderNr) throws NoResultException {
        Order existingOrder = null;
        try {
            existingOrder = orderDAO.findSingleOrder(orderNr);
        } catch (NoResultException nre) {
            System.out.println("no order found with orderNr: " + orderNr);
        }
        return existingOrder;
    }

    private Client findClient(Client newClient) {
        Client existingClient = null;

        try {
            existingClient = orderDAO.findClient(newClient);
        } catch (NoResultException nre) {
            System.out.println(
                    "client did not yet exist in DB >> new client " +
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
        // below lines won't be executed if nre is caught due to return in catch
        OrderNumberHelper helper = new OrderNumberHelper();
        helper.setLastOrderDate(lastOrder.getOrderDate());
        helper.setLastOrderNrSeq(lastOrder.getOrderNr().substring(11));

        return helper.generateOrderNr();
    }

    private void printOrderDetails(Order order) {
        System.out.println(order);
        printClient(order.getClient());
        printAddress(order.getDelivery_address());
        printProduct(order.getOrderProductList());
    }

    private void printClientDetails(Client client) {
        System.out.println(client);
        printAddress(client.getAddressList());
        List<Order> orderList = client.getOrderList();
        for (Order o : orderList) {
            System.out.println(o);
            List<Product> productList = o.getOrderProductList();
            for (Product p : productList) {
                System.out.println(p);
            }
        }
    }

    private void printOrder(List<Order> orderList) {
        for (Order o : orderList) {
            System.out.println(o);
        }
    }

    private String printOrder(Order order) {
        return order.toString();
    }

    private void printClient(List<Client> clientList) {
        for (Client c : clientList) {
            System.out.println(c);
        }
    }

    private void printClient(Client client) {
        System.out.println(client);
    }

    private void printAddress(List<Address> addressList) {
        for (Address a : addressList) {
            System.out.println(a);
        }
    }

    private void printAddress(Address address) {
        System.out.println(address);
    }

    private void printProduct(List<Product> productList) {
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    private void printProduct(Product product) {
        System.out.println(product);
    }
}
