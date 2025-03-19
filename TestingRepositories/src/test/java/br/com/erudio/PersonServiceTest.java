package br.com.erudio;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServices services;

    Person person;

    @BeforeEach
    void createPerson() {
        person = new Person("Felipe", "Souza", "felipe@gmail.com",
                "Blumenau(SC)", "Male");
    }

    @Test
    void save_WhenPersonProvided_ReturnsSavedPerson() {
        when(personRepository.save(person)).thenReturn(person);

        Person savedPerson = services.create(person);

        verify(personRepository, Mockito.times(1)).save(any(Person.class));
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals("felipe@gmail.com", savedPerson.getEmail());
    }

    @Test
    void save_WhenEmailAlreadyExists_ShouldTThrowsException() {
       when(personRepository.findByEmail(anyString())).thenReturn(Optional.of(person));

       Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           services.create(person);
       });

       verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    void findAll_WhenCalled__ShouldReturnListOfPersons() {
        Person personAux = new Person("Dani",  "Vezaro",  "dani@gmail.com",  "Bnu-City",  "Female");

        when(personRepository.findAll()).thenReturn(List.of(person, personAux));

        List<Person> personList = services.findAll();

        verify(personRepository, times(1)).findAll();
        Assertions.assertFalse(personList.isEmpty());
        Assertions.assertEquals(2, personList.size());
    }

    @Test
    void findAll_WhenCalled_ShouldReturnEmptyList() {
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        List<Person> personList = services.findAll();

        verify(personRepository, times(1)).findAll();
        Assertions.assertTrue(personList.isEmpty());
    }

    @Test
    void findById_WhenCalled_ShouldReturnPersonById () {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        Person personById = services.findById(anyLong());

        verify(personRepository, times(1)).findById(anyLong());
        Assertions.assertEquals("Felipe", personById.getFirstName());
    }

    @Test
    void update_WhenCalled_ShouldReturnUpdatedPerson() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        person.setId(1L);
        person.setEmail("arruda@gmail.com");
        person.setAddress("BNU-CITY");

        Person updatedPerson = services.update(person);

        verify(personRepository, times(1)).findById(anyLong());
        verify(personRepository, times(1)).save(any(Person.class));
        Assertions.assertEquals("arruda@gmail.com", updatedPerson.getEmail());
        Assertions.assertEquals("BNU-CITY", updatedPerson.getAddress());
    }

    @Test
    void delete_WhenCalledWithValidId_ShouldRemovePerson  () {
        person.setId(1L);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        doNothing().when(personRepository).delete(any(Person.class));

        services.delete(person.getId());

        verify(personRepository, times(1)).delete(person);
    }

}
