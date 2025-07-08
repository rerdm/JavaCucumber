package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code TestDataLoader} class provides utility methods for loading and accessing
 * test data from a properties file. Specifically, it loads URL mappings from the
 * {@code testdata/urls.properties} file at class initialization and allows retrieval
 * of URLs by website name. This class is intended to centralize and simplify access
 * to test URLs for automated tests.
 */


public class TestDataLoader {
    private static Properties urlProperties;
    private static Properties searchProperties;
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
                throw new RuntimeException("Testdaten-Datei urls.properties nicht gefunden!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der URL-Testdaten: " + e.getMessage(), e);
        }
        
        // Load search properties
        searchProperties = new Properties();
        try (InputStream input = TestDataLoader.class.getClassLoader()
                .getResourceAsStream("testdata/search_data.properties")) {
            if (input != null) {
                searchProperties.load(input);
            } else {
                System.out.println("Warning: search_data.properties nicht gefunden!");
            }
        } catch (IOException e) {
            System.out.println("Warning: Fehler beim Laden der Search-Testdaten: " + e.getMessage());
        }
        
        // Load coffeeshop properties
        coffeeshopProperties = new Properties();
        try (InputStream input = TestDataLoader.class.getClassLoader()
                .getResourceAsStream("testdata/coffeeshop_data.properties")) {
            if (input != null) {
                coffeeshopProperties.load(input);
            } else {
                System.out.println("Warning: coffeeshop_data.properties nicht gefunden!");
            }
        } catch (IOException e) {
            System.out.println("Warning: Fehler beim Laden der Coffeeshop-Testdaten: " + e.getMessage());
        }
    }
    
    public static String getUrl(String websiteName) {
        String url = urlProperties.getProperty(websiteName);
        if (url == null) {
            throw new RuntimeException("URL für Website '" + websiteName + "' nicht gefunden!");
        }
        return url;
    }
    
    public static boolean hasUrl(String websiteName) {
        return urlProperties.containsKey(websiteName);
    }
    
    // Search Data Methods
    public static String getSearchData(String key) {
        String value = searchProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Search data für Key '" + key + "' nicht gefunden!");
        }
        return value;
    }
    
    public static String getSearchTerm(String website) {
        return getSearchData(website + "_search_term");
    }
    
    public static String getExpectedResult(String website) {
        return getSearchData(website + "_expected_result");
    }
    
    public static String[] getSearchTerms() {
        String terms = getSearchData("search_terms");
        return terms.split(",");
    }
    
    public static String getUserData(String key) {
        return getSearchData("test_" + key);
    }
    
    public static boolean hasSearchData(String key) {
        return searchProperties.containsKey(key);
    }
    
    // Coffeeshop Data Methods
    public static String getCoffeeshopData(String key) {
        String value = coffeeshopProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Coffeeshop data für Key '" + key + "' nicht gefunden!");
        }
        return value;
    }
    
    public static String getEnterButtonText() {
        return getCoffeeshopData("shop_enter_button_text");
    }
    
    public static String getExpectedShopTitle() {
        return getCoffeeshopData("expected_shop_title");
    }
    
    public static String getWelcomeMessage() {
        return getCoffeeshopData("shop_welcome_message");
    }
    
    public static String[] getCoffeeProducts() {
        String products = getCoffeeshopData("coffee_products");
        return products.split(",");
    }
    
    public static String[] getCoffeePrices() {
        String prices = getCoffeeshopData("coffee_prices");
        return prices.split(",");
    }
    
    public static String getCustomerName() {
        return getCoffeeshopData("customer_name");
    }
    
    public static String getCustomerEmail() {
        return getCoffeeshopData("customer_email");
    }
    
    public static String getLocator(String elementName) {
        return getCoffeeshopData(elementName + "_locator");
    }
    
    public static boolean hasCoffeeshopData(String key) {
        return coffeeshopProperties.containsKey(key);
    }
}
