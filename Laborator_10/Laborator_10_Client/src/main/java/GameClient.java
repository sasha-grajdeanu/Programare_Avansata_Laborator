import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * this class is responsible with communication between client and server
 */
public class GameClient {
    String serverAddress = "127.0.0.1";
    int PORT = 2504;

    public GameClient() {

    }

    /**
     * this method create a connexion to server
     * <p>
     * the client reads a command from keyboard and sending this command to server
     * <p>
     * after, client receive a message from the server and print on console
     * <p>
     * if the command was stop, connexion is off
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        Socket socket = new Socket(serverAddress, PORT);
        try {
            while (true) {
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                output.println(command);
                String response = in.readLine();
                System.out.println(response);
                if (command.equals("exit")) {
                    break;
                }
                if (command.equals("stop")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server on..." + e);
        }
    }
}
