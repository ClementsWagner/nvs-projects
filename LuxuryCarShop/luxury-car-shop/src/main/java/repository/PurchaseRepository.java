package repository;

import model.Purchase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.*;

@ApplicationScoped
public class PurchaseRepository {

    @Inject
    private EntityManager em;
    private CarRepository carRepository;

    PurchaseRepository(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    private Purchase addPurchase(){
        return null;
    }

    private List<Purchase> getAllPurchases(){
        return null;
    }

    private List<Purchase> getAllPurchasesOfUser(){
        return null;
    }
}
