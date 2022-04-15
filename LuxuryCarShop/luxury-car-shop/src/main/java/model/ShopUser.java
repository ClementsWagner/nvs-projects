package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class ShopUser extends PanacheEntity {

    private String email;
    private String userPassword;

    @OneToMany(
            mappedBy = "shopUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public ShopUser() {
    }

    public static ShopUser findByEmail(String email){
        return find("email", email).firstResult();
    }

    public ShopUser(String email, String password) {
        this.email = email;
        this.userPassword = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
