import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.PersonService;
import service.PersonServiceImpl;

public class PersonServiceTest {

    static PersonService personService;
    static Person person;

    @BeforeAll
    static void setup() {
        personService = new PersonServiceImpl();
    }

    @BeforeEach
    void createDefaultPerson() {
        person = new Person("Felipe", "Arruda", "felipe@gmail.com", "Blumenau - SC", "Male");
    }

    @Test
    void createPerson_shouldReturnPerson_whenCreatePersonSucceeds() {
        Person actual = personService.createPerson(person);

        Assertions.assertNotNull(actual, "Was expecting a person object in return");
    }

    @Test
    void createPerson_shouldReturnPersonWithAllFieldsCorrect() {
        Person actual = personService.createPerson(person);

        Assertions.assertNotNull(actual.getId(), "Person id is missing");
        Assertions.assertNotNull(actual, "Was expecting a Person object in return");
        Assertions.assertEquals(person.getFirstName(), actual.getFirstName(), "First name is incorrect");
        Assertions.assertEquals(person.getLastName(), actual.getLastName(), "Last name is incorrect");
        Assertions.assertEquals(person.getEmail(), actual.getEmail(), "Email is incorrect");
        Assertions.assertEquals(person.getAddress(), actual.getAddress(), "Address is incorrect");
        Assertions.assertEquals(person.getGender(), actual.getGender(), "Gender is incorrect");
    }

    @Test
    void createPerson_withNullEmail_ShouldThrowIllegalArgumentException() {
        person.setEmail("");

        IllegalArgumentException actual = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> personService.createPerson(person),
                "Empty email should have a cause an IllegalArgumentException");


        Assertions.assertEquals(
                "The person e-mail is null or empty!",
                actual.getMessage(),
                "Exception error message is incorrect");
    }

}
