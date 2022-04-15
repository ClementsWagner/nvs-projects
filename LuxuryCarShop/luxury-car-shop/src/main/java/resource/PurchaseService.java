package resource;

import repository.PurchaseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("purchase")
public class PurchaseService {

    @Inject
    protected PurchaseRepository purchaseRepository;
}
