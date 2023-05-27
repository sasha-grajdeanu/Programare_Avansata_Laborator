package com.example.demo.Entities;
import lombok.Data;

@Data
public class Student {
    private int id;
    private String nume;
    private String prenume;
    private float medie;
    private Gen gen;
    private An an;
}
