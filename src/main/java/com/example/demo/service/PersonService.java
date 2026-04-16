package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Create person
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    // Get all persons
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    // Get person by id
    public Optional<Person> getPersonById(UUID id) {
        return personRepository.findById(id);
    }

    // Delete person
    public void deletePersonById(UUID id) {
        personRepository.deleteById(id);
    }

    // Update person
    public Person updatePerson(UUID id, Person newPerson) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    return personRepository.save(person);
                })
                .orElse(null);
    }
}