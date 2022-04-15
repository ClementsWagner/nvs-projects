package resource;

import repository.CarRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("car")
public class CarService {

    @Inject
    protected CarRepository carRepository;

}
