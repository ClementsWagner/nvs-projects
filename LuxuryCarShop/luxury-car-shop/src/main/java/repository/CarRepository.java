package repository;

import com.arjuna.ats.jta.exceptions.NotImplementedException;
import model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.*;

@ApplicationScoped
public class CarRepository {

    @Inject
    private EntityManager em;

    public List<Car> getAllAccessibleCars(){
        return null;
    }


}
