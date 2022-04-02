package com.commands;

import com.exceptions.CommandException;
import com.items.Item;
import com.organizer.Catalog;
import com.singletons.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportCommand extends CatalogCommand {

    public ReportCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand() throws CommandException
    {
        Configuration cfg = Config.getFreeMarkerConfig();

        try {
            Template template = cfg.getTemplate("report.ftl");

            OutputStream outputStream = new FileOutputStream("C:\\Users\\40736\\OneDrive\\Desktop\\PA_2022_2B4_GRIGORITA_DELIA\\Homework5\\index.html");
            Writer outputWriter = new OutputStreamWriter(outputStream, "UTF-8");

            Map<String, Object> data = new HashMap<>();

            List<Item> items = new ArrayList<>();
            for (var item : catalog.getCatalogItems())
            {
                items.add(item);
            }

            data.put("items", items);

            template.process(data, outputWriter);
        }
        catch (Exception exception) {
            System.out.println(exception);
            throw new CommandException("ReportCommand command problem", exception);
        }
    }

    @Override
    public void runGenericCommand() {}

    @Override
    public String getCommandDescription()
    {
        return "Load";
    }

}

