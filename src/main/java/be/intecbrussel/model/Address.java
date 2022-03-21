package be.intecbrussel.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "delivery_address")
    private List<Order> addressOrderList;

    @ManyToMany
//    @JoinTable(name = "address_client_table")
    private List<Client> clientList;

    // mandatory for an order - null not allowed
    @Column(name = "street_name")
    private String streetName;

    // mandatory for an order - null not allowed
    @Column(name = "house_nr")
    private int houseNr;

    @Column(name = "house_nr_sub")
    private String houseNrSub;

    // validation rules:
    // 4 digits, between 1000 and 9999 (Belgian zipCode)
    // mandatory for an order - null not allowed
    @Column(name = "zip_code")
    @NotNull
    @Range(min = 1000, max = 9999)
    private int zipCode;

    // mandatory for an order - null not allowed
    @Column(name = "city_name")
    @NotNull
    private String cityName;

    public int getId() {
        return id;
    }

    public Address setId(int id) {
        this.id = id;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public Address setHouseNr(int houseNr) {
        this.houseNr = houseNr;
        return this;
    }

    public String getHouseNrSub() {
        return houseNrSub;
    }

    public Address setHouseNrSub(String houseNrSub) {
        this.houseNrSub = houseNrSub;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public Address setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public Address setCityName(String cityName) {
        this.cityName = cityName;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getHouseNr() == address.getHouseNr() && getZipCode() == address.getZipCode() && getStreetName().equals(
                address.getStreetName()) && Objects.equals(
                getHouseNrSub(),
                address.getHouseNrSub()) && getCityName().equals(
                address.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetName(), getHouseNr(), getHouseNrSub(),
                            getZipCode(), getCityName());
    }
}
