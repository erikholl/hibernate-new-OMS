package be.intecbrussel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client_table")
public class Client {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "client")
    private List<Order> orderList;

    @ManyToMany
    private List<Address> addressList;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
