package com.example.demo.entity;

import lombok.Data;

/**
 * class: modelling a player entity
 */
@Data
public class Player {
    private String name;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
