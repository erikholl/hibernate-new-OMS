package be.intecbrussel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "delivery_address")
    private List<Order> addressOrderList;

    @ManyToMany
    private List<Client> clientList;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_nr")
    private int houseNr;

    @Column(name = "house_nr_sub")
    private String houseNrSub;

    @Column(name = "zip_code")
    private int zipCode;

    @Column(name = "city_name")
    private String cityName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    public String getHouseNrSub() {
        return houseNrSub;
    }

    public void setHouseNrSub(String houseNrSub) {
        this.houseNrSub = houseNrSub;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", houseNr=" + houseNr +
                ", houseNrSub='" + houseNrSub + '\'' +
                ", zipCode=" + zipCode +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
