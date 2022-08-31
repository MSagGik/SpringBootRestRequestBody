package ru.msaggik.spring.SpringBootRestRequestBody.util;

public class PersonNotCreatedException extends RuntimeException{
    // переопределение дефолтного конструктора
    public PersonNotCreatedException(String msg) {
        super(msg);
    }
}
