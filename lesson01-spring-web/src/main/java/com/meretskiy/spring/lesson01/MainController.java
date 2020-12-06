package com.meretskiy.spring.lesson01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//делаем класс контроллером, который будет перехватывать запросы
@Controller
public class MainController {

    //GET http://localhost:8189/spring/hello
    //помечаем метод как обработчик гет запросов
    @GetMapping("/hello")
    //только содержимое этого метода попадет в ответ, а не что либо еще(файл с таким же именем и т.д.)
    @ResponseBody
    public String sayHello() {
        return "Hello, World!!!";
    }

    //GET http://localhost:8189/spring/sum?a=10&b=20
    @GetMapping("/sum")
    @ResponseBody
    public Integer calculateSum(@RequestParam Integer a, @RequestParam Integer b) {
        return a + b;
    }

    //GET http://localhost:8189/spring/greetings?[name=Bob&surname=Marley]
    @GetMapping("/greetings")
    @ResponseBody
    //не обязательно заполнять оба параметра (required = false)
    public String greetings(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        if (name == null && surname == null) {
            return "Hello, Stranger!!!";
        }
        return "Hello, " + (name != null ? name : "Unknown") + " " + (surname != null ? surname : "Unknown");
    }
}
