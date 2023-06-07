package com.example.demo.Entities;
import lombok.Data;

/**
 * class: an oop representation of a student
 */
@Data
public class Student {
    private int id;
    private int punctaj;
    private Gen gen;
    private An an;

    public Student(int id, int medie, String gen, int an){
        this.id = id;
        this.punctaj = medie;

        if(an == 1){
            this.an = An.LICENTA_1;
        }else if(an == 2){
            this.an = An.LICENTA_2;
        }else{
            this.an = An.LICENTA_3;
        }

        if(gen.equals("M")){
            this.gen = Gen.MASCULIN;
        }else{
            this.gen = Gen.FEMININ;
        }
    }
}
