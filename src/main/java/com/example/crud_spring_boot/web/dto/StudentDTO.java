package com.example.crud_spring_boot.web.dto;

import java.time.LocalDate;
import java.time.Period;

public class StudentDTO {
    private String id;
    private String namaLengkap;
    private int usia;

    public StudentDTO(String id, String namaDepan, String namaBelakang, LocalDate tanggalLahir) {
        this.id = id;
        this.namaLengkap = namaDepan + (namaBelakang != null ? " " + namaBelakang : "");
        this.usia = calculateUsia(tanggalLahir);
    }

    public String getId() {
        return id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public int getUsia() {
        return usia;
    }

    private int calculateUsia(LocalDate tanggalLahir) {
        return Period.between(tanggalLahir, LocalDate.now()).getYears();
    }
}