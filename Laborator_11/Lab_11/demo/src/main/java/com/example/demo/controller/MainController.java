package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.entity.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * this class contains controllers for giving and modifying data
 */

@RestController
@RequestMapping("/resources")
public class MainController {
    private List<Player> players = new ArrayList<>();
    private List<Game> games = new ArrayList<>();

    public MainController() {
        players.add(new Player("Hagi", 1));
        players.add(new Player("Maradona", 2));
        games.add(new Game("Steaua"));
        games.add(new Game("Farul"));
    }

    /**
     * method: get list of players
     *
     * @return
     */
    @GetMapping("/players/list")
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * method: add a new player
     *
     * @param name
     * @return
     */
    @PostMapping("/players/add")
    public int addPlayer(@RequestParam String name) {
        int id = 1 + players.size();
        players.add(new Player(name, id));
        return id;
    }

    /**
     * method: modify data about the player
     *
     * @param id
     * @param name
     * @return
     */
    @PutMapping("/players/modify/{id}")
    public boolean modifyPlayer(@PathVariable int id, @RequestParam String name) {
        for (Player player : players) {
            if (player.getId() == id) {
                player.setName(name);
                return true;
            }
        }
        return false;
    }

    /**
     * method: delete a player
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/players/{id}")
    public boolean deletePlayer(@PathVariable int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                players.remove(player);
                return true;
            }
        }
        return false;
    }

    /**
     * method: return a list of existent games
     *
     * @return
     */
    @GetMapping("/games/list")
    public List<Game> getGames() {
        return games;
    }

    /**
     * method: add a new game
     *
     * @param name
     * @return
     */
    @PostMapping("/games/add")
    public int addGame(@RequestParam String name) {
        int id = 1 + games.size();
        games.add(new Game(name));
        return id;
    }

    /**
     * method: add a player in game
     *
     * @param nameGame
     * @param id
     * @return
     */
    @PutMapping("/games/modify/{nameGame}")
    public boolean addPlayerInGame(@PathVariable String nameGame, @RequestParam int id) {
        for (Game game : games) {
            if (game.getName().equals(nameGame)) {
                for (Player player : players) {
                    if (player.getId() == id) {
                        boolean doIt = game.addPlayer(player);
                        if (doIt) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
