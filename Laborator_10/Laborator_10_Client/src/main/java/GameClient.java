import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class is responsible to create a connection to the server and sending command and receive message from the server
 */
public class GameClient{
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 2504;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             ) {

            System.out.println("Conexiune realizata");
            String userInput;
            String mesaj;
            while (true) {
                if((mesaj = in.readLine() )!= null){
                    System.out.println(mesaj);
                }
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextLine();
                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println(serverResponse);
                if (userInput.equals("exit")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
            System.exit(1);
        }
    }
}
