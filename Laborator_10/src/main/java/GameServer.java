import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * this class is responsible with the creating connexion between client and server
 */
public class GameServer {
    public static final int PORT = 2504;
    public GameServer() throws IOException {
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
            while(true){
                System.out.println("Asteptam la portul 2504...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            serverSocket.close();
        }
    }
}
