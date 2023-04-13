import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * class represents the token
 */
@Data
@AllArgsConstructor
public class Token {
    private final int number;

    public String toString() {
        return String.valueOf(getNumber());

    }
}
