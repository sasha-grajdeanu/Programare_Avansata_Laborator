package Homework.members;

import java.time.LocalDate;

/**
 * this class is an extension of the class person and represent a designer
 */
public class Designer extends Person {
    private String style;

    public Designer(String nameOfPerson, LocalDate birthDate, String style) {
        super(nameOfPerson, birthDate);
        this.style = style;
    }

    public Designer() {
    }

    @Override
    public String toString() {
        return "Designer{" +
                "style='" + style + '\'' +
                "} " + super.toString();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
