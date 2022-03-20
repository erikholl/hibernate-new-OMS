package be.intecbrussel.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client_table")
public class Client {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "client")
    private List<Order> orderList;

    @ManyToMany
//    @JoinTable(name = "client_address_table")
    private List<Address> addressList;

    @Column(name = "client_salutation")
    @Enumerated(EnumType.STRING)
    private Salutation salutation;

    @Column(name = "client_first_name")
    private String firstName;

    @Column(name = "client_last_name_")
    private String lastName;

    @Column(name = "client_email")
    private String email;

    @Column(name = "client_password")
    private String password;

    public int getId() {
        return id;
    }

    public Client setId(int id) {
        this.id = id;
        return this;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public Client setSalutation(Salutation salutation) {
        this.salutation = salutation;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", salutation=" + salutation +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getEmail().equals(client.getEmail()) && getPassword().equals(
                client.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
