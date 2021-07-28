package com.challenge.prodig.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.challenge.prodig.exception.PersonNotFoundException;
import com.challenge.prodig.model.DocumentType;
import com.challenge.prodig.model.Person;
import com.challenge.prodig.repository.PersonRepository;
import com.challenge.prodig.service.PersonService;
import com.challenge.prodig.util.PersonTestUtil;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = { "com.challenge.prodig.service" }, lazyInit = true)
class PersonServiceTest {

    @Autowired
    @Qualifier("personServiceImpl")
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void configure() {
        personRepository.deleteAll();
    }

    @Test
    void shouldGetPersons() throws ParseException {

        // GIVEN A EMPTY DB
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Person> persons = personService.getPersons(pageRequest);

        assertNotNull(persons);
        assertTrue(persons.isEmpty());

        // ADD ONE Person
        Person personA = PersonTestUtil.makePersonA();
        personRepository.save(personA);

        // AND SEARCH again
        persons = personService.getPersons(pageRequest);

        assertNotNull(persons);
        assertFalse(persons.isEmpty());
        assertEquals(1, persons.getContent().size());
        Person person = persons.getContent().get(0);

        assertNotNull(person.getId());

        assertPersonAreEquals(personA, person);

    }

    @Test
    void shouldSavePerson() {

        // GIVEN a empty DB
        List<Person> people = personRepository.findAll();
        assertTrue(people.isEmpty());

       // @formatter:off
        Person personToSave = Person.builder()
                  .documentType(DocumentType.CUIT)
                  .documentNumber("561241")
                  .email("miemail@email.com")
                  .birthDate(new Date())
                  .build();
       // @formatter:on

        assertNull(personToSave.getId());

        Person person = personService.savePerson(personToSave);

        assertNotNull(person.getId());

        assertPersonAreEquals(personToSave, person);

    }

    @Test
    void shouldThrowGetPersonByIdIfNotExist() {

        // GIVEN a empty DB
        List<Person> people = personRepository.findAll();
        assertTrue(people.isEmpty());

        // THEN
        try {
            personService.getPersonById(-1L);
            fail();
        } catch (PersonNotFoundException e) {
            assertEquals(PersonNotFoundException.PERSON_NOT_FOUND, e.getMessage());
        }

    }

    private static void assertPersonAreEquals(Person personA, Person person) {
        assertEquals(personA.getDocumentType(), person.getDocumentType());
        assertEquals(personA.getDocumentNumber(), person.getDocumentNumber());
        assertEquals(personA.getBirthDate(), person.getBirthDate());
        assertEquals(personA.getEmail(), person.getEmail());
    }

}