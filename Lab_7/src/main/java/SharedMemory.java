import java.util.*;

public class SharedMemory {
    private final List<Token> tokens;

    public SharedMemory(int n){
        tokens = new ArrayList<>();
        for(int i = 0; i<n*n*n; i++){
            tokens.add(new Token(i));
        }
        Collections.shuffle(tokens);
    }
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
