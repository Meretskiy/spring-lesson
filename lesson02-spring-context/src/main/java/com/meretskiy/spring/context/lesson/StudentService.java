package com.meretskiy.spring.context.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

//сервисный класс в котором мы будем обращаться к репозиторию: добавлять студентов в бд или вычислять средний балл...
@Component
public class StudentService {

    //Объясняем спрингу, что нам нужно взять ссылку на этот объект чере аннотацию @Autowired.
    //Что бы когда он создаст сервис закинул нам ссылку на репозиторий из своего контекста.

//    //первый вариант, но не рекомендуемый, непосредственно над самой ссылкой
//    @Autowired
//    private StudentRepository studentRepository;

//    //Второй вариант через сеттеры
//    private StudentRepository studentRepository;
//    @Autowired
//    public void setStudentRepository(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

//    //Третий вариант через конструктор
//    private StudentRepository studentRepository;
//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    //Если анотируем через интерфейс и нужно устранить неоднозначность когда есть более одной реализации пользуем @Qualifier
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(@Qualifier("studentDBRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addDB(Student student) {

    }

    public int calculateAverageScore() {
        List<Student> students = studentRepository.getStudents();
        if (students.size() == 0) {
            return 0;
        }
        int avg = 0;
        for (Student s : students) {
            avg += s.getScore();
        }
        avg /= students.size();
        return avg;
    }
}
