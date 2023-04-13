import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;


/**
 * robot
 * explore the matrix
 * if the cell is unvisited, extract tokens and put this in the cell
 */
@Data
@AllArgsConstructor
public class Robot implements Runnable {

    Exploration explore;
    private String name;
    private boolean running = true;
    private volatile boolean waiting = false;

    public Robot(String name, Exploration explore) {
        this.name = name;
        this.explore = explore;
    }


    public void run() {
        int tokens = 0;
        int x = -1;
        int y = -1;
        boolean check = false;
        boolean sense = false;
        boolean visit = false;
        while (!visit) {
            Random random = new Random();
            x = random.nextInt(5);
            y = random.nextInt(5);
            visit = explore.getMap().visit(x, y, this);
            if (visit) {
                tokens = tokens + 5;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        while (running) {
            if (this.getExplore().getMap().isComplet()) {
                running = false;
            }
            if (waiting) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                visit = false;
                if (y == 4 && !check) {
                    x = (x + 1) % 5;
                    check = true;
                    sense = true;
                } else if (y == 0 && !check) {
                    x = (x + 1) % 5;
                    check = true;
                    sense = false;
                } else if (y == 4 && check) {
                    y = (y - 1) % 5;
                    check = false;
                } else if (y == 0 && check) {
                    y = (y + 1) % 5;
                    check = false;
                } else {
                    if (sense) {
                        y = (y - 1) % 5;
                    } else {
                        y = (y + 1) % 5;
                    }
                }
                visit = explore.getMap().visit(x, y, this);
                if (visit) {
                    tokens = tokens + 5;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(this.name + " a colectat " + tokens + " tokeni");
    }
}
