package Homework.members;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Designer designer = (Designer) o;
        return Objects.equals(style, designer.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), style);
    }
}
