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
 - add validation for entity classes
 - add update order method
 - add additional methods (check user, check orders not sent, ..)
 - convert zipCode to String
 - add convertor for salutation enum
 - embed a file with login details in persistence.xml
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
        newClient.setSalutation(Salutation.MR)
                .setFirstName("Jan")
                .setLastName("VandePopeleere")
                .setEmail("merci@nestle.com")
                .setPassword("bourgondie");

        Address newAddress = new Address();
        newAddress.setStreetName("Kerkplein")
                .setHouseNr(2)
//                .setHouseNrSub("II")
                .setZipCode(1111)
                .setCityName("Kappenou");

        Product newP1 = new Product();
        newP1.setProductName("vlonders").setAmount(4).setPricePerUnit(23.5);
        Product newP2 = new Product();
        newP2.setProductName("terrastegel x 10").setAmount(2).setPricePerUnit(19.95);
        Product newP3 = new Product();
        newP3.setProductName("zak aarde").setAmount(4).setPricePerUnit(10);

        newOrderProductList.add(newP1);
        newOrderProductList.add(newP2);
        newOrderProductList.add(newP3);

        os.saveOrder(newClient, newAddress, newOrderProductList);

        System.out.println("*********** READ ORDER ***********");
        os.readOrder("ORD-202203-0001");
//
        System.out.println("*********** FIND CLIENT BY EMAIL / PW ***********");
        os.findClientByEmailAndPw("terrier@asiel.bb", "woef");

    }
}
