package com.meretskiy.lesson.controllers;

import com.meretskiy.lesson.model.Student;
import com.meretskiy.lesson.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    //что бы предоставить thymeleaf данные для сборки страницы инжектим сюда модель
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_score") Integer minScore,
                          @RequestParam(required = false, name = "max_score") Integer maxScore
    ) {
        model.addAttribute("students", studentService.findAll(minScore, maxScore));
        return "students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentByID(@PathVariable Long id) {
        studentService.deleteById(id);
       return"redirect:/students";
    }

    @PostMapping("/add")
    public String saveOrUpdateStudents(@ModelAttribute Student student) {
        studentService.saveOrUpdete(student);
        return "redirect:/students";
    }
}
