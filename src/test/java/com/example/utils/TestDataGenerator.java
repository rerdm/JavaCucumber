package com.example.utils;

import java.util.Random;

/**
 * Utility-Klasse für dynamische Testdaten
 */
public class TestDataGenerator {
    
    private static final Random random = new Random();
    
    // Arrays für zufällige Testdaten
    private static final String[] FIRST_NAMES = {
        "Max", "Anna", "Peter", "Lisa", "Tom", "Sarah", "Mike", "Emma"
    };
    
    private static final String[] LAST_NAMES = {
        "Müller", "Schmidt", "Weber", "Fischer", "Meyer", "Wagner", "Becker"
    };
    
    private static final String[] SEARCH_TERMS = {
        "Selenium", "Cucumber", "TestNG", "JUnit", "Maven", "Jenkins", "Docker"
    };
    
    private static final String[] EMAIL_DOMAINS = {
        "gmail.com", "yahoo.com", "outlook.com", "test.com"
    };
    
    /**
     * Generiert einen zufälligen Vornamen
     */
    public static String generateFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }
    
    /**
     * Generiert einen zufälligen Nachnamen
     */
    public static String generateLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }
    
    /**
     * Generiert einen zufälligen vollständigen Namen
     */
    public static String generateFullName() {
        return generateFirstName() + " " + generateLastName();
    }
    
    /**
     * Generiert eine zufällige E-Mail-Adresse
     */
    public static String generateEmail() {
        String firstName = generateFirstName().toLowerCase();
        String lastName = generateLastName().toLowerCase();
        String domain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
        int number = random.nextInt(1000);
        
        return firstName + "." + lastName + number + "@" + domain;
    }
    
    /**
     * Generiert einen zufälligen Suchbegriff
     */
    public static String generateSearchTerm() {
        return SEARCH_TERMS[random.nextInt(SEARCH_TERMS.length)];
    }
    
    /**
     * Generiert ein zufälliges Passwort
     */
    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }
    
    /**
     * Generiert eine zufällige Zahl zwischen min und max
     */
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Generiert einen zufälligen Benutzernamen
     */
    public static String generateUsername() {
        String firstName = generateFirstName().toLowerCase();
        int number = random.nextInt(10000);
        return firstName + number;
    }
}
