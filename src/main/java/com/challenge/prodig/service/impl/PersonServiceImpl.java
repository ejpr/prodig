package com.challenge.prodig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.prodig.exception.PersonNotFoundException;
import com.challenge.prodig.model.Person;
import com.challenge.prodig.repository.PersonRepository;
import com.challenge.prodig.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Person> getPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Person savePerson(Person person) {

        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Person updatePerson(Person person) {
        getPersonById(person.getId());
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Person deletePerson(Long id) {
        Person person = getPersonById(id);
        personRepository.delete(person);
        return person;
    }

    @Transactional(readOnly = true)
    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

}
