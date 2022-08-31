package ru.msaggik.spring.SpringBootRestRequestBody.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msaggik.spring.SpringBootRestRequestBody.model.Person;
import ru.msaggik.spring.SpringBootRestRequestBody.repositories.PeopleRepository;
import ru.msaggik.spring.SpringBootRestRequestBody.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    // метод приёма объекта из контроллера и сохранения его в БД
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
