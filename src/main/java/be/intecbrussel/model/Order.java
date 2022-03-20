package be.intecbrussel.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "order_number")
    private String orderNr;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "order_client")
    private Client client;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.PERSIST)
    private List<Product> orderProductList;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address delivery_address;

    @Column(name = "is_vat_free")
    private boolean vatFree;

    @Column(name = "is_send")
    private boolean isSent;

    @Column(name = "order_date")
    private Date orderDate;

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public Order setOrderNr(String orderNr) {
        this.orderNr = orderNr;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Order setClient(Client orderClient) {
        this.client = orderClient;
        return this;
    }

    public List<Product> getOrderProductList() {
        return orderProductList;
    }

    public Order setOrderProductList(List<Product> orderProductList) {
        this.orderProductList = orderProductList;
        return this;
    }

    public Address getDelivery_address() {
        return delivery_address;
    }

    public Order setDelivery_address(Address deliveryAddress) {
        this.delivery_address = deliveryAddress;
        return this;
    }

    public boolean isVatFree() {
        return vatFree;
    }

    public Order setVatFree(boolean vatFree) {
        this.vatFree = vatFree;
        return this;
    }

    public boolean isSent() {
        return isSent;
    }

    public Order setSent(boolean sent) {
        isSent = sent;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNr='" + orderNr + '\'' +
                ", vatFree=" + vatFree +
                ", isSent=" + isSent +
                ", orderDate=" + orderDate +
                '}';
    }

    // TODO: equals and hash
}
