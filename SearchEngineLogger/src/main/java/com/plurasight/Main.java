package com.plurasight;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, User!");

        SearchEngineLogger logger = new SearchEngineLogger();

        logger.writeToLog();

    }
}