package Homework.members;

import java.time.LocalDate;

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

}
