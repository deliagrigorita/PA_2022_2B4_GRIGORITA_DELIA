import com.commands.*;
import com.items.*;
import com.organizer.Catalog;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) //am apelat functiile din main
    {
        Main program = new Main();
        //program.mandatorySerialize();
        program.mandatoryDeserialize();
    }

    public void mandatorySerialize() //functie in caream  realizat si creat cartile
    {
        Catalog catalog = new Catalog(); //am instantiat o clasa intr-un obiect

        Book[] books = IntStream.range(0, 10).mapToObj //am creat un array de carti
                //am luat un stream de intregi de la 0 la 10 si am mapat fiecare intreg la un obiect de tip carte
                (index -> new Book(index, "cartea" + index, "locatie" + index)).toArray
                (Book[]::new);

        AddCommand addCommand = new AddCommand(catalog);

        for (var book : books) //am iterat prin toate cartile
        {
            addCommand.runCommand(book);
        }

        ToStringCommand toStringCommand = new ToStringCommand(catalog);
        System.out.println(toStringCommand.runCommand()); //afisez forma ca sir de caractere a obiectului "catalog"

        SaveCommand saveCommand = new SaveCommand(catalog);

        try {
            saveCommand.runCommand("book.json"); //salvez in format json catalogul
        }
        catch (Exception ignore) {}
    }

    public void mandatoryDeserialize() //deserializez(a lua din format ul citit de om si convertit intr un format citit de calculaotr) catalogul
    {
        Catalog catalog = new Catalog();

        LoadCommand loadCommand = new LoadCommand(catalog);

        try {
            loadCommand.runCommand("book.json");//incarc in memorie
        }
        catch (Exception exception) {
             System.out.println(exception);
        }

        System.out.println(catalog.toString());

    }
}
