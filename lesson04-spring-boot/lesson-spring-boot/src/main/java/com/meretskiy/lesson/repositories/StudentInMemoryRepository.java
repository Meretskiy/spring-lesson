package com.meretskiy.lesson.repositories;

import com.meretskiy.lesson.exceptions.ResourceNotFoundException;
import com.meretskiy.lesson.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class StudentInMemoryRepository {
    private List<Student> students;


    @PostConstruct
    public void init() {
        this.students = new ArrayList<>(Arrays.asList(
                new Student(1L, "Bob", 90),
                new Student(2L, "Dag", 80),
                new Student(3L, "Pit", 70)
        ));
    }

    public Student saveOrUpdate(Student s) {
        if (s.getId() != null) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(s.getId())) {
                    students.set(i, s);
                    return s;
                }
            }
        }
        //находим среди студентов максимальный айди и сказали что наш новый студент будет иметь айди на 1 больше
        Long newId = students.stream().mapToLong(Student::getId).max().orElseGet(() -> 0L) + 1L;
        s.setId(newId);
        students.add(s);
        return s;
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(students);
    }

    //Optional - коробка(контейнер) студент там может как быть так и нет..
    public Optional<Student> findById(Long id) {
        //взяли список студентов, отфильтровали по ид, нашли одного из них
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void deleteByID(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }
}
