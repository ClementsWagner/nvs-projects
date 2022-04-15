package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Purchase extends PanacheEntity {

    @ManyToOne
    private ShopUser shopUser;
    private String CarType;

    public Purchase() {
    }

    public Purchase(ShopUser shopUser, String carType) {
        this.shopUser = shopUser;
        CarType = carType;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }
}
