package com.challenge.prodig.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.prodig.dto.PageRequestDTO;
import com.challenge.prodig.dto.PersonDTO;
import com.challenge.prodig.mapper.PageableMapper;
import com.challenge.prodig.mapper.PersonMapper;
import com.challenge.prodig.model.Person;
import com.challenge.prodig.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> getPersons(PageRequestDTO pageRequestDTO) {

        PageRequest pageRequest = PageableMapper.INSTANCE.mapPageRequestDTO(pageRequestDTO);

        Page<Person> persons = personService.getPersons(pageRequest);
        Page<PersonDTO> result = persons.map(PersonMapper.INSTANCE::mapToDTO);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@Valid @RequestBody PersonDTO personDTO) {
        // TODO implement
        return ResponseEntity.ok(personDTO);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<PersonDTO> deletePersonById(@PathParam("id") Long id) {
        // TODO implement
        return ResponseEntity.ok(new PersonDTO());
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonDTO personDTO) {
        // TODO implement
        return ResponseEntity.ok(personDTO);
    }

}
