package com.example.demo.entity;

import lombok.Data;

/**
 * class: modelling a game entity
 */
@Data
public class Game {
    private String name;
    private Player player1;
    private Player player2;

    public Game(String name) {
        this.name = name;
    }

    /**
     * method: add a player in game
     *
     * @param player
     * @return
     */
    public boolean addPlayer(Player player) {
        if (player1 == null) {
            player1 = player;
            return true;
        } else if (player2 == null) {
            player2 = player;
            return true;
        } else {
            return false;
        }
    }
}
