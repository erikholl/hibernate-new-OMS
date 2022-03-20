package be.intecbrussel.model;

import javax.persistence.*;

@Entity
@Table(name = "product_table")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
//    @JoinColumn(name = "product_line_order")
    private Order productOrder;

    @Column(name = "product_name")
    private String productName;

    private int amount;

    @Column (name = "price_per_unit")
    private double pricePerUnit;

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public Order getProductOrder() {
        return productOrder;
    }

    public Product setProductOrder(Order productOrder) {
        this.productOrder = productOrder;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Product setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public Product setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

    // TODO: equals and hash
}
