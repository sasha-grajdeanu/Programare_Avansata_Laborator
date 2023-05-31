package comma;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {

    String mode = "default";

    /**
     * method: return the locale
     * @return
     */
    public Locale getMode() {
        if (mode.equals("ro"))
            return Locale.forLanguageTag("ro");
        else
            return Locale.getDefault();
    }

    /**
     * method: set the flag for the getMode
     * @param nume
     */
    public void execute(String nume) {
        this.mode = nume;
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", this.getMode());
        String mesajUnu = messages.getString("locale.set");
        Object[] argument = {this.getMode().getDisplayLanguage()};
        String mesaj = new MessageFormat(mesajUnu).format(argument);
        System.out.println(mesaj);
    }
}
