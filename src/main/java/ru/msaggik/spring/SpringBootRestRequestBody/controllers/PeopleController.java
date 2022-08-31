package ru.msaggik.spring.SpringBootRestRequestBody.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.msaggik.spring.SpringBootRestRequestBody.model.Person;
import ru.msaggik.spring.SpringBootRestRequestBody.services.PeopleService;
import ru.msaggik.spring.SpringBootRestRequestBody.util.PersonErrorResponse;
import ru.msaggik.spring.SpringBootRestRequestBody.util.PersonNotCreatedException;
import ru.msaggik.spring.SpringBootRestRequestBody.util.PersonNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    // метод возврата данных всех пользователей
    @GetMapping()
    public List<Person> getPeople() {
        // статус - 200
        // Jackson конвертирует возвращаемые объекты в JSON
        return peopleService.findAll();
    }
    // метод возврата данных одного пользователя по id
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        // статус - 200
        // Jackson конвертирует возвращаемый объект в JSON
        return peopleService.findOne(id);
    }
    // метод обработки исключения
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {

        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        // в HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }

    // метод создания нового пользователя
    // (с помощью @Valid и BindingResult проверяется валидность введённых данных)
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // если были введены не корректные данные, то приложение ответит в формате JSON об ошибке:
            // 1) создание объекта ошибки
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            // 2) формирование исключения
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        peopleService.save(person);

        // обратная связь клиенту об успешности сохранения данных
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // 3) метод обработки исключения
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {

        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        // в HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400 статус
    }
}
