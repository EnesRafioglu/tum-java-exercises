package de.tum.in.ase.eist;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import de.tum.in.ase.eist.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testAddPerson() {
         var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        personService.save(person);

        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        personService.delete(person);

        assertTrue(personRepository.findAll().isEmpty());
    }

    @Test
    void testAddParent(){
        Person child = new Person();
        child.setBirthday(LocalDate.now());
        child.setId(1L);
        Person parent = new Person();
        parent.setBirthday(LocalDate.now());
        parent.setId(2L);
        personRepository.save(child);
        personRepository.save(parent);
        personService.addParent(child,parent);
        boolean childSaved = personService.getById(1L).isPresent();
        boolean parentSaved = personService.getById(2L).isPresent();
        boolean savedCorrectly = false;
        if(childSaved && parentSaved){
            Person newChild = personService.getById(1L).get();
            Person newParent = personService.getById(2L).get();
            savedCorrectly = newChild.getParents().contains(newParent);
        }


        assertTrue(childSaved && parentSaved && savedCorrectly);

    }

    @Test
    void testAddThreeParents(){
        Person child = new Person();
        Person parent1 = new Person();
        Person parent2 = new Person();
        Person parent3 = new Person();
        child.setId(3L);
        child.setBirthday(LocalDate.now());
        parent1.setBirthday(LocalDate.now());
        parent2.setBirthday(LocalDate.now());
        parent3.setBirthday(LocalDate.now());
         personRepository.save(child);
         personRepository.save(parent1);
         personRepository.save(parent2);
         personRepository.save(parent3);
        personService.addParent(child, parent1);
        personService.addParent(child, parent2);
        Person newChild = personService.getById(child.getId()).orElse(null);
        boolean a = newChild == null ? false:newChild.getParents().size() == 2;
        boolean b = false;
        try{
            personService.addParent(newChild,parent3);
        }
        catch (Exception e){
            b = true;
        }
        assertTrue(a && b);






    }
}
