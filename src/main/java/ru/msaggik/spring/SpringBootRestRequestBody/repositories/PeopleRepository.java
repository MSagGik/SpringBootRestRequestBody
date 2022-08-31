package ru.msaggik.spring.SpringBootRestRequestBody.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msaggik.spring.SpringBootRestRequestBody.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
