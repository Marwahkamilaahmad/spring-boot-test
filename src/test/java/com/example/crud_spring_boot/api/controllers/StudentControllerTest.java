package com.example.crud_spring_boot.api.controllers;


import com.example.crud_spring_boot.application.services.StudentService;
import com.example.crud_spring_boot.domain.models.Student;
import com.example.crud_spring_boot.web.controllers.StudentController;
import com.example.crud_spring_boot.web.dto.StudentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudentMessage() throws Exception {
        mockMvc.perform(get("/api/students/message"))
                .andExpect(status().isOk())
                .andExpect(content().string("Access to student data"));
    }

    @Test
    public void testCreateOrUpdateStudent() throws Exception {
        Student student = new Student("12345", "John", "Doe", LocalDate.of(2000, 1, 1));

        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);

        when(studentService.createOrUpdate(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testFindAllStudents() throws Exception {
        StudentDTO studentDTO = new StudentDTO("12345", "John", "Doe", LocalDate.of(2000, 1, 1));
        List<StudentDTO> students = Arrays.asList(studentDTO);
        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("12345"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    public void testFindOneStudent() throws Exception {
        Student student = new Student("12345", "John", "Doe", LocalDate.of(2000, 1, 1));
        when(studentService.findOne(anyString())).thenReturn(student);

        mockMvc.perform(get("/api/students/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testRemoveOneStudent() throws Exception {
        doNothing().when(studentService).removeOne(anyString());

        mockMvc.perform(delete("/api/students/12345"))
                .andExpect(status().isOk());
    }


}

