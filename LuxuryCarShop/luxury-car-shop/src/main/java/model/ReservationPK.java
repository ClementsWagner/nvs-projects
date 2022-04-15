package model;

import java.io.Serializable;

public class ReservationPK implements Serializable {

    protected Long shopUserId;
    protected Long carId;

    public ReservationPK(Long userId, Long carId) {
        this.shopUserId = userId;
        this.carId = carId;
    }

    public ReservationPK() {
    }

    public Long getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(Long shopUserId) {
        this.shopUserId = shopUserId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
