package com.softserve.param;

import io.github.cdimascio.dotenv.Dotenv;

public class App {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println("base.url = " + dotenv.get("base.url"));
        System.out.println("browser.name = " + dotenv.get("browser.name"));
    }
}
