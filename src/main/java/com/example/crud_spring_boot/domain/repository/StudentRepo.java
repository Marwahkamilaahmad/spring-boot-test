package com.example.crud_spring_boot.domain.repository;

import com.example.crud_spring_boot.domain.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, String> {
}
