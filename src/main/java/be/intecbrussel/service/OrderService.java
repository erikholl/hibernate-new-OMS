package be.intecbrussel.service;

import be.intecbrussel.data.OrderDAO;
import be.intecbrussel.model.Address;
import be.intecbrussel.model.Client;
import be.intecbrussel.model.Order;
import be.intecbrussel.model.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    // TODO, without any parameter input, using getters for attributes set
    //  in view layer, makes sense? can i do it?
    public void saveOrder(Client c, Address a, List<Product> pList) {
        Order orderToSave = new Order();
        for (Product p : pList) {
            p.setProductOrder(orderToSave);
        }
        orderToSave.setClient(c)
                .setDelivery_address(a)
                .setOrderProductList(pList)
                .setOrderNr("ORD-202203-0097")
                .setSent(false)
                .setVatFree(false) // TODO: implement table with few
                // countries, get value from there
                .setOrderDate(Date.valueOf(LocalDate.now()));
        orderDAO.saveOrder(orderToSave);
    }

    public void readOrder(String orderNr) {
        List<Order> orderList = orderDAO.readOrder(orderNr);
        orderList.forEach(System.out::println);
    }
}
