import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Exploration {
    private final SharedMemory mem = new SharedMemory(5);
    private final ExplorationMap map = new ExplorationMap();
    private final List<Robot> robots = new ArrayList<>();

    public void addRobot(Robot robot){
        robots.add(robot);
    }

    public void start() {
        for (Robot robot : robots) {
            new Thread(robot).start();
        }
    }
    public static void main(String[] args) {
        var explore = new Exploration();
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
    }

}
