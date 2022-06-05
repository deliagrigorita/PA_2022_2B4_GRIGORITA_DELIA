package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static Locale currentLocale ;
    public static ResourceBundle resourceBundle;
    public static void main(String[] args) {
        Locale.setDefault(Locale.UK);
        currentLocale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("res.Messages", currentLocale);
        System.out.print(resourceBundle.getString("prompt")+" ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("exit")){
            String[] params = input.split(" ");
            switch(params[0]){
                case "display": new DisplayLocales(); break;
                case "set":
                    if(params.length==3)
                        new SetLocale(params[1], params[2]);
                    else
                        System.out.println(resourceBundle.getString("invalid"));
                    break;
                case "info":
                    if(params.length==3)
                        Info.info(params[1],params[2]);
                    else
                        if(params.length==1)
                            Info.info();
                        else
                            System.out.println(resourceBundle.getString("invalid"));
                    break;
                default: System.out.println(resourceBundle.getString("invalid"));
            }
            System.out.print(resourceBundle.getString("prompt")+" ");
            input=scanner.nextLine();
        }
    }
}
