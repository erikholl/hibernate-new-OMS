package be.intecbrussel.view;

import be.intecbrussel.data.EntityManagerProvider;
import be.intecbrussel.model.*;
import be.intecbrussel.service.OrderService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/*
TODO:
 - update joinColumn / joinTable names in DB - NO SUCCESS, INVESTIGATE
 - add equals and hash to entities / use in certain methods
 - extend read order method (show client, address, productlines)
 - add update order method
 - add additional methods (check user, check orders not sent, ..)
 - add ordernr generator method
 - convert zipCode to String
 - add convertor for salutation enum
 - embed a file with login details in persistence.xml
 - remove unnecessary getters/setters
*/

public class Play {
    public static void main(String[] args) {

        EntityManager em = EntityManagerProvider.getEntityManager();
        OrderService os = new OrderService();
        List<Product> newOrderProductList = new ArrayList<>();

        System.out.println("*********** SAVE ORDER ***********");
        Client newClient = new Client();
        newClient.setSalutation(Salutation.MR)
                .setFirstName("Francis")
                .setLastName("Bacon")
                .setEmail("bacon@expressionism.org")
                .setPassword("thepopeisdead");

        Address newAddress = new Address();
        newAddress.setStreetName("Joycelane")
                .setHouseNr(284)
                .setHouseNrSub("II")
                .setZipCode(0xAABBCC)
                .setCityName("Dublin");

        Product newP1 = new Product();
        newP1.setProductName("crucifix").setAmount(2).setPricePerUnit(69.96);
        Product newP2 = new Product();
        newP2.setProductName("brush").setAmount(3).setPricePerUnit(6.06);
        Product newP3 = new Product();
        newP3.setProductName("pigment burgundy").setAmount(5).setPricePerUnit(3.03);

        newOrderProductList.add(newP1);
        newOrderProductList.add(newP2);
        newOrderProductList.add(newP3);

        os.saveOrder(newClient, newAddress, newOrderProductList);

        System.out.println("*********** READ ORDER ***********");
        os.readOrder("ORD-202203-0098");

    }
}
