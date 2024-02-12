package comma;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    /**
     * method: print available locales 
     * @param locale
     */
    public void execute(Locale locale){
        Locale[] available = Locale.getAvailableLocales();
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locale);
        String mesajUnu = messages.getString("locales");
        System.out.println(mesajUnu);
        for(Locale locales: available){
            System.out.println(locales.getDisplayName());
        }
    }
}
