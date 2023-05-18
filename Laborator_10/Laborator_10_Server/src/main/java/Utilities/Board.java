package Utilities;

import lombok.Data;

@Data
public class Board {
    private static final int SIZE = 15;
    private static final int EMPTY = 0;

    private int[][] boardTable;

    public Board() {
        boardTable = new int[SIZE][SIZE];
    }

    /**
     * this method validate a move
     *
     * @param linie
     * @param coloana
     * @return
     */
    public boolean isValid(int linie, int coloana) {
        if (linie >= 0 && linie < SIZE && coloana >= 0 && coloana < SIZE && boardTable[linie][coloana] == EMPTY) {
            return true;
        }
        return false;
    }

    /**
     * thsi method realize a move
     *
     * @param linie
     * @param coloana
     * @param player
     */
    public synchronized void move(int linie, int coloana, int player) {
        if (isValid(linie, coloana)) {
            boardTable[linie][coloana] = player;
        }
    }

    /**
     * this method check if the board is full
     *
     * @return
     */
    public boolean completeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (boardTable[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method find a possible line with 5 element
     *
     * @param player
     * @return
     */
    public boolean verificaLinii(int player) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (boardTable[row][col] == player && boardTable[row][col + 1] == player && boardTable[row][col + 2] == player && boardTable[row][col + 3] == player && boardTable[row][col + 4] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this method determine if exist a winner
     * @param player
     * @return
     */
    public boolean isWin(Player player) {
        int symbol = player.getSymbol();
        return verificaLinii(symbol) || verificaColoane(symbol) || verificaDiagonale(symbol) || verificaDiagonaleInverse(symbol);
    }

    /**
     * this method find a possible line with 5 element
     *
     * @param player
     * @return
     */
    public boolean verificaColoane(int player) {
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row <= SIZE - 5; row++) {
                if (boardTable[row][col] == player && boardTable[row + 1][col] == player && boardTable[row + 2][col] == player && boardTable[row + 3][col] == player && boardTable[row + 4][col] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this method find a possible line with 5 element
     *
     * @param player
     * @return
     */
    public boolean verificaDiagonale(int player) {
        for (int row = 0; row <= SIZE - 5; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (boardTable[row][col] == player && boardTable[row + 1][col + 1] == player && boardTable[row + 2][col + 2] == player && boardTable[row + 3][col + 3] == player && boardTable[row + 4][col + 4] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this method find a possible line with 5 element
     *
     * @param player
     * @return
     */
    public boolean verificaDiagonaleInverse(int player) {
        for (int row = 0; row < SIZE - 4; row++) {
            for (int col = 4; col < SIZE; col++) {
                if (boardTable[row][col] == player && boardTable[row][col] == boardTable[row + 1][col - 1] && boardTable[row][col] == boardTable[row + 2][col - 2]
                        && boardTable[row][col] == boardTable[row + 3][col - 3] && boardTable[row][col] == boardTable[row + 4][col - 4]) {
                    return true;
                }
            }
        }
        return false;
    }

}
