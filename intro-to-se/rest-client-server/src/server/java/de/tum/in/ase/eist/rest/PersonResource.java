package de.tum.in.ase.eist.rest;

import de.tum.in.ase.eist.model.Note;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.service.PersonService;
import de.tum.in.ase.eist.util.PersonSortingOptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        if(person.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        personService.savePerson(person);
        return ResponseEntity.ok(person);
    }
    @PutMapping("persons/{personId}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person toChange, @PathVariable("personId") UUID personId) {
        if (!toChange.getId().equals(personId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personService.savePerson(toChange));
    }
    @DeleteMapping("persons/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") UUID personId) {
        personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("persons")
    public ResponseEntity<List<Person>> getAllPersons(@RequestParam(value = "sortField", defaultValue = "") PersonSortingOptions.SortField sortField
            ,@RequestParam(value = "sortingOrder",defaultValue = "") PersonSortingOptions.SortingOrder sortingOrder) {
        if(sortingOrder == null) sortingOrder = PersonSortingOptions.SortingOrder.ASCENDING;
        if(sortField == null) sortField = PersonSortingOptions.SortField.ID;
        return ResponseEntity.ok(personService.getAllPersons(new PersonSortingOptions(sortingOrder,sortField)));
    }
}
