import Utilities.Game;
import Utilities.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * this class represents the core of the management and playing the game
 */
public class ClientThread extends Thread {
    private Socket clientSocket;
    private ArrayList<Game> games;
    private PrintWriter out;
    private BufferedReader in;
    private String nume;
    private Game currentGame;
    private String message = "RUN";

    public ClientThread(Socket clientSocket, ArrayList<Game> games) {
        this.clientSocket = clientSocket;
        this.games = games;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * this method receive the command and call required function
     */
    @Override
    public void run() {
        try {
            out.println(message);
            String inputLine;
            while (true) {
                out.flush();
                inputLine = in.readLine();
                String[] input = inputLine.split(" ");
                switch (input[0]) {
                    case "create":
                        Game game = new Game(input[1]);
                        games.add(game);
                        out.println("S-a creat jocul cu numele " + game.getNume());
                        out.println(message);
                        break;
                    case "join":
                        currentGame = findGameByNume(input[1]);
                        assert currentGame != null;
                        nume = currentGame.getNume();
                        if (currentGame != null) {
                            boolean success = currentGame.addPlayer(clientSocket);
                            if (success) {
                                out.println("S-a intrat in jocul: " + currentGame.getNume());
                                sendToPairedPlayer("Adversaul a intrat in joc");
                            } else {
                                out.println("REFUZ");
                                sendToPairedPlayer(" ");
                            }
                        } else {
                            out.println("NU EXISTA JOCUL ACESTA");
                            sendToPairedPlayer(" ");
                        }
                        break;
                    case "move":
                        if (currentGame != null) {
                            int linie = Integer.parseInt(input[1]);
                            int coloana = Integer.parseInt(input[2]);
                            int success = currentGame.makeMove(clientSocket, linie, coloana);
                            if (success == 0) {
                                if (currentGame.getBoard().isWin(currentGame.getPlayer(clientSocket))) {
                                    out.println("AI CASTIGAT");
                                    sendToPairedPlayer("Adversarul a castigat. Mutarea castigatoare a fost" + linie + " " + coloana);
                                    currentGame = null;
                                    reset(nume);

                                } else if (currentGame.getBoard().completeBoard()) {
                                    out.println("REMIZA");
                                    sendToPairedPlayer("REMIZA. Mutarea castigatoare a fost" + linie + " " + coloana);
                                    currentGame = null;
                                    reset(nume);
                                } else {
                                    out.println("Mutare efectuata");
                                    sendToPairedPlayer("Adversarul a mutat la pozitia " + linie + ", " + coloana);
                                }

                            } else if (success == 1) {
                                out.println("NU SE POATE MUTA");
                                sendToPairedPlayer(" ");
                            } else if (success == 2) {
                                out.println("NU E TURA TA");
                                sendToPairedPlayer(" ");
                            } else {
                                out.println("CEVA NU E IN REGULA AZ-5 PLEASE");
                                sendToPairedPlayer(" ");
                            }
                        }else{
                            out.println("JOC NEINITIAT");
                            sendToPairedPlayer(" ");
                        }
                        break;
                    case "exit":
                        out.println("SERVER OPRIT");
                        out.println(" ");
                        currentGame = null;
                        reset(nume);
                        return;
                    default:
                        out.println("COMANDA INVALIDA");
                        sendToPairedPlayer(" ");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }

    /**
     * this method is responsible to find required game
     * @param nume
     * @return
     */
    private Game findGameByNume(String nume) {
        for (Game game : games) {
            if (Objects.equals(game.getNume(), nume)) {
                return game;
            }
        }
        return null;
    }

    private void reset(String name){
        for (Game game : games) {
            if (Objects.equals(game.getNume(), nume)) {
                games.remove(game);
            }
        }
    }

    /**
     * this method is responsible to sent to opponent messages
     * @param message
     */
    private void sendToPairedPlayer(String message) {
        Game game;
        if ((game = findGameByNume(nume)) != null) {
            Player pairedPlayer = game.getPairedPlayer(clientSocket);
            if (pairedPlayer != null) {
                try {
                    PrintWriter pairedPlayerOut = new PrintWriter(pairedPlayer.getPlayerSocket().getOutputStream(), true);
                    pairedPlayerOut.println(message);
                } catch (IOException e) {
                    System.out.println("Eroare: " + e.getMessage());
                }
            }
        }
    }
}
