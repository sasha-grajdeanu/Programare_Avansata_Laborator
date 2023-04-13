import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Data
public class SharedMemory {
    private final List<Token> tokens;

    /**
     * responsible with initialization of the shared memory, in the random manner
     *
     * @param n dimension of the map
     */
    public SharedMemory(int n) {
        tokens = new ArrayList<>();
        for (int i = 0; i < n * n * n; i++) {
            tokens.add(new Token(i));
        }
        Collections.shuffle(tokens);
    }

    /**
     * method responsible with extraction of the tokens
     *
     * @param howMany
     * @return
     */
    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.remove(new Random().nextInt(tokens.size())));
        }
        return extracted;
    }
}
