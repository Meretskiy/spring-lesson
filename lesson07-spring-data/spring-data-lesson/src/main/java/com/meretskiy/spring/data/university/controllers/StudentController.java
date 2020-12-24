package com.meretskiy.spring.data.university.controllers;

import com.meretskiy.spring.data.university.model.Student;
import com.meretskiy.spring.data.university.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

//todo почитать
//рест контроллер - аннотация которая говорит, что возвращаемые данные будут не страницы, а json
//отличается от обычного контроллера тем, что над всеми его методами автоматом проставляется @ResponseBody
//а обычный контроллер по умолчанию пытается вернуть страничку
@RestController
//путь по которому будут перехватываться запросы
@RequestMapping("/students")
//через аннотацию инжектим все final поля
@RequiredArgsConstructor
public class StudentController {

    private final StudentsRepository studentsRepository;

    //хотим показать список всех студентов
    @GetMapping
    public List<Student> getAllStudents() {
        return (List<Student>) studentsRepository.findAll();
    }

    //хотим вернуть студента
    //из path variable берем id и прокидываем его в метод репозитория
    @GetMapping("/{id}")
    public Student getStudentsById(@PathVariable Long id) {
        return studentsRepository.findById(id).get();
    }

    @GetMapping("/search_by_name")
    public Student searchByName(@RequestParam String name) {
        return studentsRepository.findByName(name).get();
    }

    @GetMapping("/search_by_min_score")
    public List<Student> searchByMinScore(@RequestParam(name = "min_score") Integer minScore) {
        return studentsRepository.findAllByScoreGreaterThan(minScore);
    }

    @GetMapping("/test")
    public Student testMethod() {
        return studentsRepository.customFindByIdAndName(1L, "Bob").get();
    }

    //в теле запроса делаем студента и передаем его пост запросом
    //когда к нам пост запрос приходит, мы из тела пост запроса достаем json преобразуем его с помощью джексона
    //в настоящий объект, этот объект сохраняем, получаем от хибернейта айдишник и возвращаем клиенту
    @PostMapping
    //можно заставить все действия в методе выполняться единой транзакцией с помощью аннотации
    @Transactional
    public Student save(@RequestBody Student student) {
        student.setId(null);
        return studentsRepository.save(student);
    }
}
