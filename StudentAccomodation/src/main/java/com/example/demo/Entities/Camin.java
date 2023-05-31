package com.example.demo.Entities;

import lombok.Data;

@Data
public class Camin {
    private int id;
    private int capacitateM;
    private int capacitateF;

    public Camin(int id, int capacitateF, int capacitateM){
        this.id = id;
        this.capacitateF = capacitateF;
        this.capacitateM = capacitateM;
    }

}
