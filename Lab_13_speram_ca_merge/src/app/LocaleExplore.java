package app;

import comma.DisplayLocales;
import comma.Info;
import comma.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * This class: read command from keyboard and call the called function
 */


public class LocaleExplore {
    public static void main(String[] main){
        Info info = new Info();
        DisplayLocales displayLocales = new DisplayLocales();
        SetLocale locales = new SetLocale();

        while(true){
            Scanner scanner = new Scanner(System.in);

            String command  = scanner.nextLine();
            ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locales.getMode());
            String mesajUnu = messages.getString("prompt");
            System.out.println(mesajUnu + command);
            if(command.equals("exit")){
                break;
            }else if(command.equals("display")){
                displayLocales.execute(locales.getMode());
            }else if(command.equals("set")){
                System.out.println("Introdu locatia");
                String type = scanner.nextLine();
                locales.execute(type);
            } else if (command.equals("info")) {
                info.execute(locales.getMode());
            }else{
                String error  = messages.getString("invalid");
                System.out.println(error);
            }

        }
    }
}
