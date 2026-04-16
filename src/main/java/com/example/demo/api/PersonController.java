package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/person")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Create person
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    // Get all persons
    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    // Get person by ID
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    // Delete person
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") UUID id) {
        personService.deletePersonById(id);
    }

    // Update person
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") UUID id,
                               @Valid @NotNull @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }
}