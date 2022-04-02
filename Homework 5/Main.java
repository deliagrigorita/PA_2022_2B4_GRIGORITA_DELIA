import com.commands.*;
import com.exceptions.CommandException;
import com.items.*;
import com.organizer.Catalog;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args)
    {
        Main program = new Main();
        program.mandatorySerialize();
        //program.mandatoryDeserialize();
    }

    public void mandatorySerialize()
    {
        Catalog catalog = new Catalog();

        Book[] books = IntStream.range(0, 10).mapToObj
                (index -> new Book(index, "cartea" + index, "locatie" + index)).toArray
                (Book[]::new);

        AddCommand addCommand = new AddCommand(catalog);

        for (var book : books)
        {
            addCommand.runCommand(book);
        }

        ToStringCommand toStringCommand = new ToStringCommand(catalog);
        //System.out.println(toStringCommand.runCommand());

        SaveCommand saveCommand = new SaveCommand(catalog);

        try {
            saveCommand.runCommand("C:\\Users\\40736\\OneDrive\\Desktop\\PA_2022_2B4_GRIGORITA_DELIA\\catalog.json");
        }
        catch (Exception ignore) {}

        ListCommand listCommand = new ListCommand(catalog);
        listCommand.runCommand();

        ViewCommand viewCommand = new ViewCommand(catalog, "C:\\Users\\40736\\OneDrive\\Desktop\\PA_2022_2B4_GRIGORITA_DELIA\\item.txt");
        try {
            viewCommand.runCommand(2);
        }
        catch (CommandException commandException)
        {
            System.out.println(commandException);
        }

        ReportCommand reportCommand = new ReportCommand(catalog);
        try {
            reportCommand.runCommand();
        }
        catch (CommandException commandException)
        {
            System.out.println(commandException);
        }
    }

    public void mandatoryDeserialize()
    {
        Catalog catalog = new Catalog();

        LoadCommand loadCommand = new LoadCommand(catalog);

        try {
            loadCommand.runCommand("C:\\Users\\40736\\OneDrive\\Desktop\\PA_2022_2B4_GRIGORITA_DELIA\\Homework5\\catalog.json");
        }
        catch (Exception exception) {
             System.out.println(exception);
        }

        System.out.println(catalog.toString());

    }
}
