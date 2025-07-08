package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Minimal TestDataLoader for Coffeeshop Tests
 */
public class TestDataLoader {
    private static Properties urlProperties;
    private static Properties coffeeshopProperties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {

        // Load URL properties
        urlProperties = new Properties();
        try (InputStream input = TestDataLoader.class.getClassLoader()
                .getResourceAsStream("testdata/urls.properties")) {
            if (input != null) {
                urlProperties.load(input);
            } else {
                throw new RuntimeException("Test data file urls.properties not found!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading URL test data: " + e.getMessage(), e);
        }
        
        // Load coffeeshop_data properties
        coffeeshopProperties = new Properties();
        try (InputStream input = TestDataLoader.class.getClassLoader()
                .getResourceAsStream("testdata/coffeeshop_data.properties")) {
            if (input != null) {
                coffeeshopProperties.load(input);
            } else {
                throw new RuntimeException("Coffeeshop file coffeeshop_data.properties not found!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading coffeeshop test data: " + e.getMessage(), e);
        }
    }
    
    public static String getUrl(String websiteName) {
        String url = urlProperties.getProperty(websiteName);
        if (url == null) {
            throw new RuntimeException("URL for website '" + websiteName + "' not found!");
        }
        return url;
    }
    
    public static String getCoffeeshopData(String key) {
        String value = coffeeshopProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Coffeeshop data for key '" + key + "' not found!");
        }
        return value;
    }
}
