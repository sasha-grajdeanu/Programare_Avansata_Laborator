import Utilities.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    private static int port = 2504;
    private static ArrayList<Game> games = new ArrayList<>();

    /**
     * this method is responsible to create the connection between clients and server
     * @param args
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Asteptam la portul " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientThread clientThread = new ClientThread(clientSocket, games);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
            System.exit(1);
        }
    }
}