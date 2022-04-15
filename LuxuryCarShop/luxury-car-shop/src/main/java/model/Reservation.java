package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.time.ZonedDateTime;
import java.util.*;
import javax.persistence.*;

@Entity
public class Reservation extends PanacheEntityBase {

    @EmbeddedId
    private ReservationPK id = new ReservationPK();

    @ManyToOne
    @MapsId("shopUserId")
    private ShopUser shopUser;

    @ManyToOne
    @MapsId("carId")
    private Car car;

    private ZonedDateTime pickUpDate;
}
