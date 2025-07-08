package com.example.stepdefinitions;

import com.example.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks für Test-Setup und Teardown
 */
public class Hooks {
    
    @Before
    public void setUp() {
        System.out.println("Test wird gestartet...");
        // WebDriver wird lazy initialisiert in DriverManager
    }
    
    @After
    public void tearDown() {
        System.out.println("Test wird beendet...");
        DriverManager.quitDriver();
    }
}
