package com.challenge.prodig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.challenge.prodig.model.Person;
import com.challenge.prodig.repository.PersonRepository;
import com.challenge.prodig.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Person> getPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
