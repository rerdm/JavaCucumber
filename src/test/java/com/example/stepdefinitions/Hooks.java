package com.example.stepdefinitions;

import com.example.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks class for Cucumber test setup and teardown operations.
 * 
 * Purpose:
 * - Hooks provide a way to run setup and cleanup code before and after each test scenario
 * - @Before method executes before each Cucumber scenario starts
 * - @After method executes after each Cucumber scenario completes (regardless of pass/fail)
 * - Ensures proper WebDriver lifecycle management and prevents resource leaks
 * - Provides a centralized place for test initialization and cleanup
 * 
 * Benefits:
 * - Automatic resource management (no manual cleanup needed in step definitions)
 * - Consistent test environment setup for each scenario
 * - Proper browser cleanup prevents multiple browser instances
 * - Better test isolation and reliability
 */
public class Hooks {
    
    @Before
    public void setUp() {
        System.out.println("Starting test scenario...");
        // WebDriver will be initialized in DriverManager when first accessed
    }
    
    @After
    public void tearDown() {
        System.out.println("Finishing test scenario...");
        DriverManager.quitDriver();
    }
}
