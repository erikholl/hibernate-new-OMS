package be.intecbrussel.view;

import be.intecbrussel.model.*;
import be.intecbrussel.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/*
TODO:
 - update joinColumn / joinTable names in DB - NO SUCCESS, INVESTIGATE
 - add validation for entity classes
 - add update order method
 - add additional methods (check user, check orders not sent, ..)
 - convert zipCode to String
 - add convertor for salutation enum
 - remove unnecessary getters/setters
 Functionalities:
        // create order new client - X
        // find / return client with email / pw - X
        // create order existing client - X
        // update order products client side
        // update order delivery status
        // change address existing client
        // read order with all details (client, address, products) - X mwoah
        // read client with address and orders
*/

public class Play {
    public static void main(String[] args) {

        OrderService os = new OrderService();
        List<Product> newOrderProductList = new ArrayList<>();

        System.out.println("*********** SAVE ORDER ***********");
        Client newClient = new Client();
        newClient.setSalutation(Salutation.MRS)
                .setFirstName("Sofia")
                .setLastName("Gubaidulina")
                .setEmail("orthodoxsound@russiansrebels.org")
                .setPassword("sevenwords");

        Address newAddress = new Address();
        newAddress.setStreetName("Mowglistreet")
                .setHouseNr(1)
//                .setHouseNrSub("b")
                .setZipCode(1234)
                .setCityName("Kristopol");

        Product newP1 = new Product();
        newP1.setProductName("dreamcatcher").setAmount(2).setPricePerUnit(6);
        Product newP2 = new Product();
        newP2.setProductName("tuning fork").setAmount(1).setPricePerUnit(99);
        Product newP3 = new Product();
        newP3.setProductName("scratching post").setAmount(1).setPricePerUnit(21);
//        Product newP4 = new Product();
//        newP4.setProductName("kalsleren sportstoel").setAmount(2).setPricePerUnit(1200);

        newOrderProductList.add(newP1);
        newOrderProductList.add(newP2);
        newOrderProductList.add(newP3);
//        newOrderProductList.add(newP4);

        os.saveOrder(newClient, newAddress, newOrderProductList);

        System.out.println("*********** READ ORDER ***********");
        os.readOrder("ORD-202203-0001");
        os.readOrder("ORD-202203-0000"); // TODO: output m when not found
//
//        System.out.println("*********** FIND CLIENT BY EMAIL / PW ***********");
//        os.findClientByEmailAndPw("terrier@asiel.bb", "woef");

    }
}
