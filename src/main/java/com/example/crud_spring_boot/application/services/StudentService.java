package com.example.crud_spring_boot.application.services;

import com.example.crud_spring_boot.domain.models.Student;
import com.example.crud_spring_boot.domain.repository.StudentRepo;
import com.example.crud_spring_boot.web.dto.StudentDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student createOrUpdate(Student student) {
        return studentRepo.save(student);
    }

    public Student findOne(String id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<StudentDTO> findAll() {
        return ((List<Student>) studentRepo.findAll()).stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getNamaDepan(),
                        student.getNamaBelakang(),
                        student.getTanggalLahir()))
                .collect(Collectors.toList());
    }

    public void removeOne(String id) {
        studentRepo.deleteById(id);
    }
}
