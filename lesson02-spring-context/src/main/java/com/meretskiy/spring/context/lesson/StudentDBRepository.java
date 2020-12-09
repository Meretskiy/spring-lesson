package com.meretskiy.spring.context.lesson;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDBRepository implements StudentRepository{
    @Override
    public List<Student> getStudents() {
        return new ArrayList<>();
    }
}
