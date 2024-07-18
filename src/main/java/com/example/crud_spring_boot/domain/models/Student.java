package com.example.crud_spring_boot.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student implements Serializable {

    @Id
    private String id; // nomor induk mahasiswa


    @Column(nullable = false)
    private String namaDepan;

    @Column
    private String namaBelakang;

    @Column(nullable = false)
    private LocalDate tanggalLahir;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(String id, String namaDepan, String namaBelakang, LocalDate tanggalLahir) {
        this.id = id;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.tanggalLahir = tanggalLahir;
    }
}

