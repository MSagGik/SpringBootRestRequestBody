package ru.msaggik.spring.SpringBootRestRequestBody.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RequestMapping("/api")
//public class FirstRESTController {
//
//    @ResponseBody // данная аннотация сигнализирует отсутствие представления,
//    // т.к. в данном методе возвращаются данные
//    @GetMapping("/sayHello")
//    public String sayHello() {
//        // пример возвращаемых данных, передаваемых в формате JSON
//        return "Hello world!";
//    }
//}

@RestController // данная аннотация сигнализирует отсутствие представления
// по всем методам данного класса
@RequestMapping("/api")
public class FirstRESTController {

    @GetMapping("/sayHello")
    public String sayHello() {
        // пример возвращаемых данных, передаваемых в формате JSON
        return "Hello world!";
    }
}