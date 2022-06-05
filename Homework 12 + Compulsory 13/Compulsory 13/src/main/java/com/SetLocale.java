package com;

import app.LocaleExplore;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public SetLocale(String language, String country){
        Locale[] locales = Locale.getAvailableLocales();
        for(Locale locale : locales){
            if(!locale.getLanguage().equals("")&& !locale.getCountry().equals("") &&
                locale.getLanguage().equalsIgnoreCase(language)&&
                locale.getCountry().equalsIgnoreCase(country)){
                LocaleExplore.currentLocale = locale;
                LocaleExplore.resourceBundle = ResourceBundle.getBundle("res/Messages", LocaleExplore.currentLocale);
                String pattern = LocaleExplore.resourceBundle.getString("locale.set");
                Object[] arguments = {LocaleExplore.currentLocale.getDisplayName()};
                String message = new MessageFormat(pattern).format(arguments);
                System.out.println(message);
                return;
            }
        }
        System.out.println(LocaleExplore.resourceBundle.getString("notAvailable"));
    }
}
