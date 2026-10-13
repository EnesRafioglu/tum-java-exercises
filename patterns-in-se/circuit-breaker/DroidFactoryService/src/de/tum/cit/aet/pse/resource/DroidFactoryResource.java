package de.tum.cit.aet.pse.resource;


import de.tum.cit.aet.pse.model.Droid;
import de.tum.cit.aet.pse.model.DroidFactory;
import de.tum.cit.aet.pse.model.DroidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping(value = "/droid/", consumes = "application/json")
public class DroidFactoryResource {
    private final Function<DroidType, Droid> func = DroidFactory::produceDroid;

    @Autowired
    private CircuitBreaker<DroidType, Droid> circuitBreaker;


    @PostMapping("r2")
    public ResponseEntity<Droid> produceR2(@RequestBody DroidType droidType) {
        if (!droidType.equals(DroidType.R2)) {
            throw new BadRequestException();
        }
        return circuitBreaker.protectedCall(droidType, func);

    }

    @PostMapping("3po")
    public ResponseEntity<Droid> produce3PO(@RequestBody DroidType droidType) {
        if (!droidType.equals(DroidType.THREE_PO)) {
            throw new BadRequestException();
        }
        return circuitBreaker.protectedCall(droidType, func);
    }

    @PostMapping("astromech")
    public ResponseEntity<Droid> produceAstromech(@RequestBody DroidType droidType) {
        if (!droidType.equals(DroidType.ASTROMECH)) {
            throw new BadRequestException();
        }
        return circuitBreaker.protectedCall(droidType, func);
    }


}
