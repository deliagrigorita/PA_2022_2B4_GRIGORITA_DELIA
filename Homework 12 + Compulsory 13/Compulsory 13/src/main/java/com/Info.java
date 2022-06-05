package com;

import app.LocaleExplore;
import sun.util.locale.LanguageTag;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void info(){
        printInfo(LocaleExplore.currentLocale, LocaleExplore.resourceBundle);
    }
    public static void info(String language, String country){
        Locale[] locales = Locale.getAvailableLocales();
        for(Locale locale : locales){
            if(!locale.getLanguage().equals("")&& !locale.getCountry().equals("") &&
                locale.getLanguage().equalsIgnoreCase(language)&&
                locale.getCountry().equalsIgnoreCase(country)){
                    printInfo(locale, LocaleExplore.resourceBundle);
                    return;
            }
        }
        System.out.println(LocaleExplore.resourceBundle.getString("notAvailable"));
    }

    private static void printInfo(Locale locale, ResourceBundle resourceBundle) {
        String pattern = resourceBundle.getString("info");
        Object[] arguments = {locale.getDisplayName()};
        String message = new MessageFormat(pattern).format(arguments);
        System.out.println(message);
        System.out.println(resourceBundle.getString("country") + " " + locale.getDisplayCountry()
                +" ("+locale.getDisplayCountry(LocaleExplore.currentLocale)+")");
        System.out.println(resourceBundle.getString("language") + " " + locale.getDisplayLanguage()
                +" ("+locale.getDisplayLanguage(LocaleExplore.currentLocale)+")");
        Currency currency = Currency.getInstance(locale);
        System.out.println(resourceBundle.getString("currency") + " " + currency.getCurrencyCode()+" ("+currency.getDisplayName()+")");
        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        System.out.print(resourceBundle.getString("weekDays")+ " ");
        for (String string : dateFormatSymbols.getWeekdays())
            System.out.print(string + " ");
        System.out.println();
        System.out.print(resourceBundle.getString("months")+ " ");
        for(String string : dateFormatSymbols.getMonths()){
            System.out.print(string + " ");
        }
        System.out.println();
        System.out.print(resourceBundle.getString("today")+ " ");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(locale);
        DateTimeFormatter formatter1 = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.getDefault());
        System.out.println(today.format(formatter1)+ " (" + today.format(formatter)+ ")");
    }
}
