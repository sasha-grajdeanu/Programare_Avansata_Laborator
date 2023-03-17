package Homework.members;

import java.time.LocalDate;
import java.util.Objects;

/**
 * this class is an extension of the class person and represent a programmer
 */
public class Programmer extends Person {
    private String language;

    public Programmer(String nameOfPerson, LocalDate birthDate, String language) {
        super(nameOfPerson, birthDate);
        this.language = language;
    }

    public Programmer() {
    }

    @Override
    public String toString() {
        return "Programmer{" + "language='" + language + '\'' + "} " + super.toString();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Programmer that = (Programmer) o;
        return language.equals(that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language);
    }
}
