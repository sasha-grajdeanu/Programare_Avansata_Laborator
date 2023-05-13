import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * this class is responsible with receiving and sending messages between client and server
 */
public class ClientThread extends Thread {

    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Implementation of the run method
     * <p>
     * receive message from client
     * <p>
     * if the message is stop sending a message and connexion is close
     * <p>
     * otherwise sending a message of confirmation and connexion is on
     */
    public void run() {
        try {
            while (true) {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = input.readLine();
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                if (request.equals("stop")) {
                    String respond = "Server stopped";
                    output.println(respond);
                    output.flush();
                    break;
                } else {
                    String respond = "Server received the request...";
                    output.println(respond);
                    output.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
