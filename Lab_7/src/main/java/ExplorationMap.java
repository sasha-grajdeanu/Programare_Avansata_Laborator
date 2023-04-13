import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * the matrix
 */
@Data
public class ExplorationMap {
    private final List<Token>[][] matrix;

    public ExplorationMap() {
        this.matrix = new ArrayList[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.matrix[i][j] = new ArrayList<>();
            }
        }
    }


    /**
     * check if the cell is unvisited
     * if is unvisited extract the tokens and put in the cell
     *
     * @param row
     * @param col
     * @param robot
     * @return true/false
     */
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if (matrix[row][col].isEmpty()) {
                List<Token> extracted = robot.getExplore().getMem().extractTokens(5);
                matrix[row][col] = extracted;
                System.out.println("Misiune reusita" + matrix[row][col]);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * check if the matrix is completed
     *
     * @return true/false
     */
    public boolean isComplet() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder matrix = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix.append(this.matrix[i][j]);
            }
            matrix.append('\n');
        }
        return matrix.toString();
    }
}


