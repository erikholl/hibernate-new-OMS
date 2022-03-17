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

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "productOrder")
    private List<Product> orderProductList;

    @ManyToOne
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

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(String orderNr) {
        this.orderNr = orderNr;
    }

    public Client getClient() {
        return client;
    }

    public Order setClient(Client orderClient) {
        this.client = orderClient;
        return this;
    }

    public Address getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(Address deliveryAddress) {
        this.delivery_address = deliveryAddress;
    }

    public boolean isVatFree() {
        return vatFree;
    }

    public void setVatFree(boolean vatFree) {
        this.vatFree = vatFree;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
}
