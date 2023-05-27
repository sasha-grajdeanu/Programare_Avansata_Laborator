package com.example.client;

import com.example.client.resources.Game;
import com.example.client.resources.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Run implements CommandLineRunner {
    private String URL = "http://localhost:8085/resources";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
        usePlayerServices();
        useGamesServices();
    }

    /**
     * method: testing rest services for the game
     */
    private void useGamesServices() {
        addGame("Dinamo");
        getGames();
        addPlayerInGame("Steaua", 1);
        addPlayerInGame("Dinamo", 2);
    }

    /**
     * method: call put service for the game
     *
     * @param name
     * @param id
     */
    private void addPlayerInGame(String name, int id) {
        String url = URL + "/games/modify/{gameName}?id={playerId}";
        restTemplate.put(url, null, name, id);
        System.out.println("S-A ADAUGAT JUCATORUL CU ID " + id + " IN JOCUL : " + name);
    }

    /**
     * method: call get service for the game
     */
    private void getGames() {
        String url = URL + "/games/list";
        ResponseEntity<Game[]> responseEntity = restTemplate.getForEntity(url, Game[].class);
        Game[] games = responseEntity.getBody();
        System.out.println("JOCURI:");
        for (Game game : games) {
            System.out.println(game.toString());
        }
    }

    /**
     * method: call post service for the game
     *
     * @param name
     */
    private void addGame(String name) {
        String url = URL + "/games/add?name=" + name;
        int gameId = restTemplate.postForObject(url, null, Integer.class);
        System.out.println("S-A ADAUGAT JOC CU ID: " + gameId);
    }

    /**
     * method: test the rest services for the player
     */
    private void usePlayerServices() {
        addPlayer("Ianis Hagi");
        addPlayer("Florinel Coman");
        modifyPlayer(1, "Gica Hagi");
        getPlayers();
        deletePlayer(3);
        getPlayers();
    }

    /**
     * method: call delete service for the player
     *
     * @param id
     */
    private void deletePlayer(int id) {
        String url = URL + "/players/{id}";
        restTemplate.delete(url, id);
        System.out.println("STERS JUCATOR CU ID: " + id);
    }


    /**
     * method: call put service for the player
     *
     * @param id
     * @param name
     */
    private void modifyPlayer(int id, String name) {
        String url = URL + "/players/modify/{id}?name={name}";
        restTemplate.put(url, null, id, name);
        System.out.println("MODIFICAT JUCATOR ID:" + id);
    }

    /**
     * method: call post service for the player
     *
     * @param name
     */
    private void addPlayer(String name) {
        String url = URL + "/players/add?name=" + name;
        int playerId = restTemplate.postForObject(url, null, Integer.class);
        System.out.println("ADAUGAT JUCATOR ID: " + playerId);
    }

    /**
     * method: call get service for the player
     */
    private void getPlayers() {
        String url = URL + "/players/list";
        ResponseEntity<Player[]> responseEntity = restTemplate.getForEntity(url, Player[].class);
        Player[] players = responseEntity.getBody();
        System.out.println("JUCATORI:");
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }


}
