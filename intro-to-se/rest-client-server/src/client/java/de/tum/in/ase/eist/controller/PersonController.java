package de.tum.in.ase.eist.controller;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.util.PersonSortingOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PersonController {

    private WebClient webClient;
    private List<Person> persons;

    public PersonController(){
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        persons = new ArrayList<>();
    }

    public void addPerson(Person person, Consumer<List<Person>> personsConsumer) {
        webClient.post()
                .uri("persons")
                .bodyValue(person)
                .retrieve()
                .bodyToMono(Person.class)
                .onErrorStop()
                .subscribe(x -> {
                    persons.add(x);
                    personsConsumer.accept(persons);
                });
    }

    public void updatePerson(Person person, Consumer<List<Person>> personsConsumer) {
        webClient.put()
                .uri("persons/" + person.getId())
                .bodyValue(person)
                .retrieve()
                .bodyToMono(Person.class)
                .onErrorStop()
                .subscribe(x -> {
                    persons.replaceAll(p -> p.getId().equals(x.getId()) ? x : p);
                    personsConsumer.accept(persons);
                });
    }

    public void deletePerson(Person person, Consumer<List<Person>> personsConsumer) {
        webClient.delete()
                .uri("persons/" + person.getId())
                .retrieve()
                .toBodilessEntity()
                .onErrorStop()
                .subscribe(x -> {
                    persons.removeIf(a -> person.getId().equals(a.getId()));
                    personsConsumer.accept(persons);
                });



    }

    public void getAllPersons(PersonSortingOptions sortingOptions, Consumer<List<Person>> personsConsumer) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("persons")
                        .queryParam("sortField", sortingOptions.getSortField())
                        .queryParam("sortingOrder", sortingOptions.getSortingOrder())
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Person>>() {
                })
                .onErrorStop()
                .subscribe(newPersons -> {
                    persons.clear();
                    persons.addAll(newPersons);
                    personsConsumer.accept(persons);
                });
    }
}
