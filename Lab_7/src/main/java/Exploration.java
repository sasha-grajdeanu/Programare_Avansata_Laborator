import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * the exploration
 * is responsible with initialization of robots and start the threads
 */
@Data
public class Exploration {
    private final SharedMemory mem = new SharedMemory(5);
    private final ExplorationMap map = new ExplorationMap();
    private final List<Robot> robots = new ArrayList<>();

    public void populate() {
        robots.add(new Robot("Wall-E", this));
        robots.add(new Robot("R2D2", this));
        robots.add(new Robot("Optimus Prime", this));
    }

    public void start() {
        for (Robot robot : robots) {
            new Thread(robot).start();
        }
    }
}
