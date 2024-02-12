package com.example.client.resources;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Player() {

    }
}
