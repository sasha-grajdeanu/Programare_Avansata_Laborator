package Utilities;

import lombok.Data;

import java.net.Socket;

/**
 * this class represent a player
 */
@Data
public class Player {
    private Socket playerSocket;
    private int symbol;
    private boolean turn;

    public Player(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }
}
