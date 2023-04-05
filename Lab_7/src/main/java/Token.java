import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    private final int number;

    public String toString(){
        String number = String.valueOf(getNumber());
        return number;

    }
}
