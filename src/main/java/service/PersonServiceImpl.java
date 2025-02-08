package service;

import model.Person;

import java.util.Random;

public class PersonServiceImpl implements PersonService {

    @Override
    public Person createPerson(Person person) {
        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new IllegalArgumentException("The person e-mail is null or empty!");
        }
        person.setId(new Random().nextLong(1000));
        return person;
    }
}
