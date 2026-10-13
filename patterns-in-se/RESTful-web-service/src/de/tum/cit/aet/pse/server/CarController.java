package de.tum.cit.aet.pse.server;

import de.tum.cit.aet.pse.model.Car;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarController {

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return ModelStorage.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable String id) {
        Car result = ModelStorage.getCarById(id);
        if (result == null) {
            throw new CarNotFoundException();
        }

        return result;
    }

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car newCar) {
        newCar.setId(ModelStorage.createRandomId());
        ModelStorage.saveCar(newCar);
        return newCar;
    }


    @PutMapping("/cars")
    public Car updateCar(@RequestBody Car updatedCar) {
        ModelStorage.saveCar(updatedCar);
        return updatedCar;
    }

    @DeleteMapping("/cars/{id}")
    public Car deleteCar(@PathVariable String id) {
        Car toDelete = ModelStorage.getCarById(id);
        if (toDelete == null) {
            throw new CarNotFoundException();
        }

        ModelStorage.deleteCar(toDelete);
        return toDelete;
    }
}
