package com.singletons;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.nio.file.Paths;

public class Config {
    private static Configuration freeMarkerConfig;

    public static Configuration getFreeMarkerConfig()
    {
        if (freeMarkerConfig == null)
        {
            freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_29);

            try {
                freeMarkerConfig.setDirectoryForTemplateLoading(
                        Paths.get("C:\\Users\\40736\\OneDrive\\Desktop\\PA_2022_2B4_GRIGORITA_DELIA\\Homework5\\BibliographyManagementSystem\\src\\main\\resources\\templates")
                                .toFile());
            }
            catch (Exception ignore) {}

            freeMarkerConfig.setDefaultEncoding("UTF-8");
            freeMarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            freeMarkerConfig.setLogTemplateExceptions(false);
            freeMarkerConfig.setWrapUncheckedExceptions(true);
            freeMarkerConfig.setFallbackOnNullLoopVariable(false);
        }

        return freeMarkerConfig;
    }
}
