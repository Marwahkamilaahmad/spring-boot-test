package com.example.crud_spring_boot.web.controllers;

import com.example.crud_spring_boot.domain.models.Student;
import com.example.crud_spring_boot.application.services.StudentService;
import com.example.crud_spring_boot.web.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/message")
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok("Access to student data");
    }
    @PostMapping
    public Student createOrUpdate(@RequestBody Student student) {
        return studentService.createOrUpdate(student);
    }
    @GetMapping
    public List<StudentDTO> findAll() {
        return studentService.findAll();
    }
    @GetMapping("/{id}")
    public Student findOne(@PathVariable("id") String id) {
        return studentService.findOne(id);
    }
    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") String id) {
        studentService.removeOne(id);
    }
}
