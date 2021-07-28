package com.challenge.prodig.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.challenge.prodig.model.Person;
import com.challenge.prodig.service.PersonService;
import com.challenge.prodig.util.PersonTestUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void configure() {
        reset(personService);
    }

    @Test
    void shouldGetPersons() throws Exception {

        // GIVEN
        List<Person> people = PersonTestUtil.makeListPerson();
        PageImpl<Person> responseToMock = new PageImpl<>(people, PageRequest.of(1, 20), people.size());
        doReturn(responseToMock).when(personService).getPersons(any());

        // WHEN

        // @formatter:off
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.get("/person")
                                      .param("page" ,"3")
                                      .param("size","30")
                                      .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));


        // THEN
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.size", equalTo(20)))
               .andExpect(jsonPath("$.content[0].id", equalTo(1)))
               .andExpect(jsonPath("$.content[0].documentType", equalTo("CUIT")))
               .andExpect(jsonPath("$.content[0].documentNumber", equalTo("123456")))
               .andExpect(jsonPath("$.content[0].birthDate", notNullValue()))
               .andExpect(jsonPath("$.content[0].email", equalTo("youremail@email.com")))
               .andExpect(jsonPath("$.content[1].id", equalTo(2)))
               .andExpect(jsonPath("$.content[1].documentType", equalTo("DNI")))
               .andExpect(jsonPath("$.content[1].documentNumber", equalTo("0000")))
               .andExpect(jsonPath("$.content[1].birthDate", notNullValue()))
               .andExpect(jsonPath("$.content[1].email", equalTo("youremail2@email.com")))
               .andReturn();
       // @formatter:on

        // AND
        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService, times(1)).getPersons(pageableArgumentCaptor.capture());
        assertEquals(3, pageableArgumentCaptor.getValue().getPageNumber());
        assertEquals(30, pageableArgumentCaptor.getValue().getPageSize());

    }

    @Test
    void shouldGetPersonsWithOutPage() throws Exception {

        // GIVEN
        List<Person> people = new ArrayList<>();
        PageImpl<Person> responseToMock = new PageImpl<>(people, PageRequest.of(1, 20), 0);
        doReturn(responseToMock).when(personService).getPersons(any());

        // WHEN

        // @formatter:off
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.get("/person")
                                      .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));


        // THEN
        actions.andExpect(status().isOk());
        // @formatter:on

        // AND
        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService, times(1)).getPersons(pageableArgumentCaptor.capture());
        assertEquals(0, pageableArgumentCaptor.getValue().getPageNumber());
        assertEquals(20, pageableArgumentCaptor.getValue().getPageSize());

    }
}