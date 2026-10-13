package de.tum.cit.aet.pse.server;


import de.tum.cit.aet.pse.model.User;
import de.tum.cit.aet.pse.model.Rental;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @GetMapping("/users/{userId}/rentals")
    public List<Rental> getAllActiveRentalsByUser(@PathVariable String userId) {
        return ModelStorage
                .getAllRentals()
                .stream()
                .filter(rental -> rental.getUser().getId().equals(userId)
                        && isActive(rental))
                .toList();


    }

    private EntityModel<User> toEntityModel(User user) {

        EntityModel<User> model = EntityModel.of(user);

        model.add(linkTo(methodOn(UserController.class)
                .getUserById(user.getId()))
                .withSelfRel());

        model.add(linkTo(methodOn(UserController.class)
                .getAllUsers())
                .withRel("users"));

        if (hasActiveRental(user)) {
            model.add(linkTo(methodOn(UserController.class)
                    .getAllActiveRentalsByUser(user.getId()))
                    .withRel("rentals"));
        }

        return model;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUserById(@PathVariable String id) {
        User user = ModelStorage.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return toEntityModel(user);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getAllUsers() {

        List<EntityModel<User>> result = ModelStorage.getAllUsers()
                .stream()
                .map(this::toEntityModel)
                .toList();

        return CollectionModel.of(result,
                linkTo(methodOn(UserController.class)
                        .getAllUsers())
                        .withSelfRel());
    }



    private boolean hasActiveRental(User user) {
        return ModelStorage
                .getAllRentals()
                .stream()
                .anyMatch(rental -> isActive(rental)
                        && rental.getUser().getId().equals(user.getId()));
    }


    private boolean isActive(Rental rental) {
        return rental.getStartDate().isBefore(LocalDateTime.now())
                && rental.getEndDate().isAfter(LocalDateTime.now());
    }
}
