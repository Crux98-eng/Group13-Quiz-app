package com.futurebrands.finalbiblequiz;



/**
 * Launcher class to avoid module system issues with JavaFX
 * This class should NOT extend Application because javafx will complain about dependece issues
 * to avoid that we preload it with some empty dependencies
 */
public class App {
    public static void main(String[] args) {
        // Set JavaFX system properties to avoid module issues
        System.setProperty("javafx.preloader", "");
        System.setProperty("java.awt.headless", "false");

        // Launch the actual JavaFX application
        Main.main(args);
    }
}