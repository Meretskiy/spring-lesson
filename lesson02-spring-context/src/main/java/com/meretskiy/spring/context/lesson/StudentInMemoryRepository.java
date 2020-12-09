package com.meretskiy.spring.context.lesson;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Primary
public class StudentInMemoryRepository implements StudentRepository {
    private List<Student> students;

    @Override
    public List<Student> getStudents() {
        //что бы никто не смог изменить, добавить данные - создаем обертку над листом которая запрещает выполнять
        // операции по изменению
        return Collections.unmodifiableList(students);
    }

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1L, "Bob", 100));
        students.add(new Student(2L, "Jon", 100));
    }

    public void add(Student student) {
        if (student.getScore() > 100 || student.getScore() < 0) {
            throw new IllegalArgumentException("Student's score don't interval [0 - 100]");
        }
        students.add(student);
    }
}
