package com;

import app.LocaleExplore;
import java.util.Locale;

public class DisplayLocales {
    public DisplayLocales(){
        System.out.println(LocaleExplore.resourceBundle.getString("default"));
        System.out.println(Locale.getDefault().getLanguage() + " " +
                Locale.getDefault().getCountry() + " - " + Locale.getDefault().getDisplayName(LocaleExplore.currentLocale));
        System.out.println(LocaleExplore.resourceBundle.getString("locales"));
        Locale[] available = Locale.getAvailableLocales();
        for(Locale locale : available) {
            if(!locale.getLanguage().equals("")&& !locale.getCountry().equals("")) {
                System.out.println(
                        locale.getLanguage() +" ("+ locale.getDisplayLanguage(LocaleExplore.currentLocale) + ")\t\t"+
                        locale.getCountry() + " ("+ locale.getDisplayCountry(LocaleExplore.currentLocale)+")");
            }
        }

    }

}
