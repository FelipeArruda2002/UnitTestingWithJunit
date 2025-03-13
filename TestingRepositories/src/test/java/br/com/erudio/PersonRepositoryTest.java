package br.com.erudio;

import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    private Person person;

    @BeforeEach
    void createPerson() {
        person = new Person("Felipe", "Souza", "felipe@gmail.com", "Blumenau(SC)", "Male");
    }

    @Test
    void save_WhenPersonProvided_ReturnsSavedPerson() {
        person = personRepository.save(person);

        Assertions.assertNotNull(person);
        Assertions.assertTrue(person.getId() > 0);
    }

    @Test
    void findAll_WhenCalled_ReturnsPersonList() {
        person = personRepository.save(person);

        List<Person> personList = personRepository.findAll();

        Assertions.assertNotNull(personList);
        Assertions.assertEquals(1, personList.size());
        Assertions.assertTrue(personList.contains(person));
    }

    @Test
    void findById_WhenPersonIdProvided_ReturnsPerson() {
        person = personRepository.save(person);

        Person personById = personRepository.findById(person.getId()).get();

        Assertions.assertNotNull(personById);
        Assertions.assertEquals(person.getId(), personById.getId());
    }

    @Test
    void findByEmail_WhenPersonEmailProvided_ReturnsPerson() {
        person = personRepository.save(person);

        Person personByEmail = personRepository.findByEmail(person.getEmail()).get();

        Assertions.assertNotNull(personByEmail);
        Assertions.assertEquals(person.getEmail(), personByEmail.getEmail());
    }

    @Test
    void save_WhenChangedPersonData_ReturnsUpdatedPerson() {
       personRepository.save(person);

        Person personById = personRepository.findById(person.getId()).get();
        personById.setEmail("souza@gmail.com");
        personById.setAddress("Curitiba(PR)");

        Person updatedPerson = personRepository.save(personById);

        Assertions.assertNotNull(updatedPerson);
        Assertions.assertEquals("souza@gmail.com", updatedPerson.getEmail());
        Assertions.assertEquals("Curitiba(PR)", updatedPerson.getAddress());
    }

    @Test
    void delete_WhenCalled_ThenDeletePerson() {
        personRepository.save(person);

        personRepository.delete(person);

        Optional<Person> personById = personRepository.findById(person.getId());

        Assertions.assertTrue(personById.isEmpty());
    }

    @Test
    void findByJPQL_WhenValidFirstAndLastName_ThenReturnsPerson() {
        personRepository.save(person);

        Person byJPQL = personRepository.findByJPQL("Felipe", "Souza");

        Assertions.assertNotNull(byJPQL);
        Assertions.assertEquals(person.getFirstName(), byJPQL.getFirstName());
        Assertions.assertEquals(person.getLastName(), byJPQL.getLastName());
    }

    @Test
    void findByJPQLNamedParameters_WhenValidFirstAndLastName_ThenReturnsPerson() {
        personRepository.save(person);

        Person byJPQLNamedParameters = personRepository.findByJPQLNamedParameters("Felipe", "Souza");

        Assertions.assertNotNull(byJPQLNamedParameters);
        Assertions.assertEquals(person.getFirstName(), byJPQLNamedParameters.getFirstName());
        Assertions.assertEquals(person.getLastName(), byJPQLNamedParameters.getLastName());
    }

    @Test
    void findByNativeSQL_WhenValidFirstAndLastName_ThenReturnsPerson() {
        personRepository.save(person);

        Person byNativeSQL = personRepository.findByNativeSQL("Felipe", "Souza");

        Assertions.assertNotNull(byNativeSQL);
        Assertions.assertEquals(person.getFirstName(), byNativeSQL.getFirstName());
        Assertions.assertEquals(person.getLastName(), byNativeSQL.getLastName());
    }

    @Test
    void findByNativeSQLwithNamedParameters_WhenValidFirstAndLastName_ThenReturnsPerson() {
        personRepository.save(person);

        Person byNativeSQLwithNamedParameters = personRepository.findByNativeSQLwithNamedParameters("Felipe", "Souza");

        Assertions.assertNotNull(byNativeSQLwithNamedParameters);
        Assertions.assertEquals(person.getFirstName(), byNativeSQLwithNamedParameters.getFirstName());
        Assertions.assertEquals(person.getLastName(), byNativeSQLwithNamedParameters.getLastName());
    }
}
