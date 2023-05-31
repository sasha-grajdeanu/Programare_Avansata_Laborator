package comma;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    /**
     * method:  print information
     * @param locale
     */
    public void execute(Locale locale){
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locale);
        String mesajUnu = messages.getString("info");
        Object[] argument = {locale.getDisplayCountry()};
        String mesaj = new MessageFormat(mesajUnu).format(argument);
        System.out.println(mesaj);
        System.out.println("Language: "+ locale.getDisplayLanguage(locale));
        StringBuilder sb = new StringBuilder();
        for(String string: DateFormatSymbols.getInstance(locale).getMonths()){
            sb.append(string).append(", ");
        }
        System.out.println("Months: " + sb.toString());
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formator = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
        System.out.println("Today: " + today.format(formator));
    }
}
