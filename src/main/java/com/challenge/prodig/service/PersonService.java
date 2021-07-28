package com.challenge.prodig.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.challenge.prodig.model.Person;

public interface PersonService {

    Page<Person> getPersons(Pageable pageable);

    Person savePerson(Person person);

    Person updatePerson(Person person);

    Person deletePerson(Long id);

    Person getPersonById(Long id);
}
