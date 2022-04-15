package repository;

import model.ShopUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Objects;

@ApplicationScoped
public class UserRepository {

    @Inject
    private EntityManager em;


    public ShopUser addUser(ShopUser user){
        return em.merge(user);
    }

    public void removeUser(Long userId){

    }

    public boolean authenticateUser(String email, String password){

        ShopUser user = ShopUser.findByEmail(email);
        return Objects.equals(user.getUserPassword(), password);
    }
}
