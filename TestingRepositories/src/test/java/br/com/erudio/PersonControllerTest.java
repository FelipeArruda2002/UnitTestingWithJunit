package br.com.erudio;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockmvc;

    @MockBean
    PersonServices service;

    Person person;

    @BeforeEach
    void createPerson() {
        person = new Person("Felipe", "Souza", "felipe@gmail.com",
                "Blumenau(SC)", "Male");
    }

    @Test
    void save_WhenPersonProvided_ReturnsSavedPerson() throws Exception {
        Mockito.when(service.create(Mockito.any(Person.class)))
                .thenAnswer((invocation) -> invocation.getArgument(0));

        // Criando uma requisição simulada
        ResultActions response = mockmvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)));

        // Realizando ações após a execução da requisição
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", Matchers.is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.is(person.getLastName())))
                .andExpect(jsonPath("$.email", Matchers.is(person.getEmail())));
    }

    @Test
    void findAll_WhenCalled__ShouldReturnListOfPersons() throws Exception {
        Mockito.when(service.findAll()).thenReturn(List.of(person, new Person()));

        ResultActions response = mockmvc.perform(get("/person"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    void findById_WhenCalled_ShouldReturnPersonById() throws Exception {
        Long personId = 1L;
        Mockito.when(service.findById(anyLong())).thenReturn(person);

        ResultActions response = mockmvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", Matchers.is(person.getFirstName())));
    }

    @Test
    void findById_WhenCalled_ShouldThrowsException() throws Exception {
        Long personId = 1L;
        Mockito.when(service.findById(anyLong())).thenThrow(ResourceNotFoundException.class);

        ResultActions response = mockmvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void update_WhenCalled_ShouldReturnUpdatedPerson() throws Exception {
        Person updatedPerson = new Person("Felipe", "Arruda Souza", "felipe@gmail.com",
                "Blu-City", "Male");


        Mockito.when(service.update(any(Person.class))).thenAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockmvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.lastName", Matchers.is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.address", Matchers.is(updatedPerson.getAddress())));
    }

    @Test
    void update_WhenCalled_ShouldThrowsException() throws Exception {
        Person updatedPerson = new Person("Felipe", "Arruda Souza", "felipe@gmail.com",
                "Blu-City", "Male");

        Mockito.when(service.update(any(Person.class))).thenThrow(ResourceNotFoundException.class);

        ResultActions response = mockmvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void delete_WhenCalledWithValidId_ShouldRemovePerson() throws Exception {
        long personId = 1L;
        Mockito.doNothing().when(service).delete(personId);

        ResultActions response = mockmvc.perform(delete("/person/{id}", personId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

}
