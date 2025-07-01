package com.softserve.param;

public class AppProperties {

    public static void main(String[] args) {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        System.out.println("base.url = " + propertiesUtil.readBaseUrl());
        System.out.println("browser.name = " + propertiesUtil.readBrowserName());
    }
}
