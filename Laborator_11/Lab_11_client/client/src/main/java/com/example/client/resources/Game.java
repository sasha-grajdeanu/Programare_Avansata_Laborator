package com.example.client.resources;

import lombok.Data;

@Data
public class Game {
    private String name;
    private Player player1;
    private Player player2;

    public Game(String name) {
        this.name = name;
    }

    public Game(){

    }
}
