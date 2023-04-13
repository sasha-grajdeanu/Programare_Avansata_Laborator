import java.util.Scanner;

/**
 * In this section, we start the program, and the supervisor sends the signals to the threads, after receiving a signal from the user
 */
public class Main {
    public static void main(String[] args) {
        Exploration exploration = new Exploration();
        Timekeeper timekeeper = new Timekeeper(60000, exploration);
        exploration.populate();
        timekeeper.start();
        exploration.start();
        while (true) {
            if (exploration.getMap().isComplet()) {
                break;
            } else {
                Scanner s = new Scanner(System.in);
                String command = s.nextLine();
                if (command.equals("o")) {
                    for (int i = 0; i < exploration.getRobots().size(); i++) {
                        exploration.getRobots().get(i).setWaiting(true);
                    }
                }
                if (command.equals("i")) {
                    for (int i = 0; i < exploration.getRobots().size(); i++) {
                        exploration.getRobots().get(i).setWaiting(false);
                    }
                }
                if (command.equals("0") || command.equals("1") || command.equals("2")) {
                    int number = Integer.valueOf(command);
                    exploration.getRobots().get(number).setWaiting(!exploration.getRobots().get(number).isWaiting());
                }
            }
        }
    }
}
