package Utilities;

import lombok.Data;

import java.net.Socket;

@Data
public class Game {
    private String nume;
    private Board board;
    private Player player1;
    private Player player2;

    public Game(String nume) {
        this.nume = nume;
        this.board = new Board();
    }

    /**
     * this method add players in game
     * @param socket
     * @return
     */
    public synchronized boolean addPlayer(Socket socket) {
        if (player1 == null) {
            player1 = new Player(socket);
            player1.setTurn(true);
            player1.setSymbol(1);
            return true;
        } else if (player2 == null) {
            player2 = new Player(socket);
            player2.setSymbol(2);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method return the opponent of a player
     * @param socket
     * @return
     */
    public Player getPairedPlayer(Socket socket) {
        if (player1 != null && player1.getPlayerSocket() == socket) {
            return player2;
        } else if (player2 != null && player2.getPlayerSocket() == socket) {
            return player1;
        }
        return null;
    }

    /**
     * this method realise a move in board
     * @param socket
     * @param linie
     * @param coloana
     * @return
     */
    public synchronized int makeMove(Socket socket, int linie, int coloana) {
        Player current = getPlayer(socket);
        if (current != null) {
            if (current.isTurn()) {
                if (board.isValid(linie, coloana)) {
                    board.move(linie, coloana, current.getSymbol());
                    current.setTurn(false);
                    Player paired = getPairedPlayer(socket);
                    if (paired != null) {
                        paired.setTurn(true);
                    }
                    return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }
        return 3;
}

    /**
     * this method return a player
     * @param socket
     * @return
     */
    public Player getPlayer(Socket socket) {
        if (player1 != null && player1.getPlayerSocket() == socket) {
            return player1;
        } else if (player2 != null && player2.getPlayerSocket() == socket) {
            return player2;
        }
        return null;
    }
}
