package com.meretskiy.lesson.services;

import com.meretskiy.lesson.model.Student;
import com.meretskiy.lesson.repositories.StudentInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentInMemoryRepository studentInMemoryRepository;

    public Optional<Student> findById(Long id) {
        return studentInMemoryRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentInMemoryRepository.findAll();
    }

    public List<Student> findAll(Integer minScore, Integer maxScore) {
        List<Student> out = findAll();
        if (minScore != null) {
            out = out.stream().filter(s -> s.getScore() >= minScore).collect(Collectors.toList());
        }
        if (maxScore != null) {
            out = out.stream().filter(s -> s.getScore() <= maxScore).collect(Collectors.toList());
        }
        return out;
    }

    public Student saveOrUpdete(Student student) {
        return studentInMemoryRepository.saveOrUpdate(student);
    }

    public void deleteById(Long id) {
        studentInMemoryRepository.deleteByID(id);
    }
}
