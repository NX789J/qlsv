package com.example.lab3.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import com.example.lab3.entity.Student;
import com.example.lab3.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listStudents(@RequestParam(required = false) String keyword) {
        return studentService.search(keyword);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable @NonNull UUID id) {
        return studentService.getById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody @NonNull Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        student.setId(id);
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable @NonNull UUID id) {
        studentService.delete(id);
    }
}