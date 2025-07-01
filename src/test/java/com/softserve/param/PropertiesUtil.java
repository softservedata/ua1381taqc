package com.softserve.param;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    private final String PATH_SEPARATOR = "/";
    private final String BASE_URL = "base.url";
    private final String BROWSER_NAME = "browser.name";
    //
    private Properties appProps;
    private String filename;

    public PropertiesUtil() {
        this("application-test.properties");
    }

    public PropertiesUtil(String filename) {
        this.filename = filename;
        readProperties();
    }

    private String getFullPath() {
        String path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath();
        System.out.println("\tpath = " + path);
        return path;
    }

    private void readProperties() {
        appProps = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(getFullPath())) {
            appProps.load(fileInputStream);
        } catch (Exception e){
            System.out.println("ERROR Reading " + filename + "  Message = " + e.getMessage());
        }
    }

    public String readBaseUrl() {
        return appProps.getProperty(BASE_URL, "https://www.greencity.cx.ua/#/ubs");
    }

    public String readBrowserName() {
        return appProps.getProperty(BROWSER_NAME, "Crome");
    }
}
       