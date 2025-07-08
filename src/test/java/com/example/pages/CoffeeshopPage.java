package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

/**
 * Page Object für Coffeeshop Website
 */
public class CoffeeshopPage extends BasePage {
    
    private WebDriverWait wait;
    
    // Locatoren für Coffeeshop Homepage
    @FindBy(css = "button[data-test='enter-shop']")
    private WebElement enterShopButton;
    
    @FindBy(css = "button:contains('Enter')")
    private WebElement enterButtonAlternative;
    
    @FindBy(xpath = "//button[contains(text(), 'Enter')]")
    private WebElement enterButtonByText;
    
    @FindBy(css = "button")
    private List<WebElement> allButtons;
    
    // Shop Page Locatoren
    @FindBy(css = "h1[data-test='shop-title']")
    private WebElement shopTitle;
    
    @FindBy(css = ".shop-header")
    private WebElement shopHeader;
    
    @FindBy(css = "[data-test='product-list']")
    private WebElement productList;
    
    @FindBy(css = ".product-item")
    private List<WebElement> products;
    
    @FindBy(css = "[data-test='cart-icon']")
    private WebElement cartIcon;
    
    @FindBy(css = ".cart-counter")
    private WebElement cartCounter;
    
    // Allgemeine Elemente
    @FindBy(tagName = "h1")
    private WebElement pageHeading;
    
    @FindBy(css = ".welcome-message")
    private WebElement welcomeMessage;
    
    public CoffeeshopPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Klickt auf den "Enter Shop" Button
     */
    public void clickEnterShopButton() {
        try {
            // Versuche zuerst den spezifischen Locator
            wait.until(ExpectedConditions.elementToBeClickable(enterShopButton));
            enterShopButton.click();
            System.out.println("Enter Shop Button geklickt (data-test)");
        } catch (Exception e1) {
            try {
                // Fallback: Button mit Text "Enter"
                wait.until(ExpectedConditions.elementToBeClickable(enterButtonByText));
                enterButtonByText.click();
                System.out.println("Enter Shop Button geklickt (xpath text)");
            } catch (Exception e2) {
                // Fallback: Suche nach Button mit "Enter" im Text
                clickButtonContainingText("Enter");
                System.out.println("Enter Shop Button geklickt (text search)");
            }
        }
        waitForPageLoad();
    }
    
    /**
     * Hilfsmethode: Klickt auf Button der bestimmten Text enthält
     */
    private void clickButtonContainingText(String text) {
        for (WebElement button : allButtons) {
            if (button.getText().toLowerCase().contains(text.toLowerCase()) ||
                button.getAttribute("value") != null && 
                button.getAttribute("value").toLowerCase().contains(text.toLowerCase())) {
                button.click();
                return;
            }
        }
        throw new RuntimeException("Button mit Text '" + text + "' nicht gefunden!");
    }
    
    /**
     * Überprüft ob der Enter Shop Button sichtbar ist
     */
    public boolean isEnterShopButtonVisible() {
        try {
            return enterShopButton.isDisplayed();
        } catch (Exception e) {
            // Fallback: Suche nach Button mit "Enter" Text
            for (WebElement button : allButtons) {
                if (button.isDisplayed() && 
                    (button.getText().toLowerCase().contains("enter") ||
                     (button.getAttribute("value") != null && 
                      button.getAttribute("value").toLowerCase().contains("enter")))) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * Überprüft ob die Shop-Seite geladen wurde
     */
    public boolean isOnShopPage() {
        try {
            // Warte kurz auf Seitenladevorgang
            Thread.sleep(2000);
            
            // Verschiedene Indikatoren für Shop-Seite prüfen
            String currentUrl = getCurrentUrl();
            String pageTitle = getPageTitle();
            
            // URL-basierte Prüfung
            if (currentUrl.contains("shop") || currentUrl.contains("cart") || 
                !currentUrl.equals(driver.getCurrentUrl())) {
                return true;
            }
            
            // Title-basierte Prüfung
            if (pageTitle.toLowerCase().contains("shop") || 
                pageTitle.toLowerCase().contains("cart") ||
                pageTitle.toLowerCase().contains("coffee")) {
                return true;
            }
            
            // Element-basierte Prüfung
            return isShopElementsVisible();
            
        } catch (Exception e) {
            System.out.println("Fehler bei Shop-Seiten-Prüfung: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Überprüft ob Shop-spezifische Elemente sichtbar sind
     */
    public boolean isShopElementsVisible() {
        try {
            // Prüfe auf verschiedene mögliche Shop-Elemente
            if (isElementPresent(By.cssSelector("[data-test='shop-title']")) ||
                isElementPresent(By.cssSelector(".shop-header")) ||
                isElementPresent(By.cssSelector("[data-test='product-list']")) ||
                isElementPresent(By.cssSelector(".product-item")) ||
                isElementPresent(By.cssSelector("[data-test='cart-icon']"))) {
                return true;
            }
            
            // Fallback: Prüfe auf generische Shop-Indikatoren
            return isElementPresent(By.xpath("//h1[contains(text(), 'Shop')]")) ||
                   isElementPresent(By.xpath("//h1[contains(text(), 'Coffee')]")) ||
                   isElementPresent(By.xpath("//div[contains(@class, 'product')]")) ||
                   isElementPresent(By.xpath("//button[contains(text(), 'Add')]"));
                   
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Hilfsmethode: Prüft ob Element vorhanden ist
     */
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gibt den Shop-Titel zurück
     */
    public String getShopTitle() {
        try {
            if (shopTitle.isDisplayed()) {
                return shopTitle.getText();
            }
        } catch (Exception e) {
            // Fallback
            try {
                return pageHeading.getText();
            } catch (Exception e2) {
                return getPageTitle();
            }
        }
        return "";
    }
    
    /**
     * Überprüft ob Produkte angezeigt werden
     */
    public boolean areProductsVisible() {
        try {
            return products.size() > 0 && products.get(0).isDisplayed();
        } catch (Exception e) {
            return isElementPresent(By.xpath("//div[contains(@class, 'product')]"));
        }
    }
    
    /**
     * Gibt die Anzahl der sichtbaren Produkte zurück
     */
    public int getProductCount() {
        try {
            return products.size();
        } catch (Exception e) {
            try {
                return driver.findElements(By.xpath("//div[contains(@class, 'product')]")).size();
            } catch (Exception e2) {
                return 0;
            }
        }
    }
    
    /**
     * Überprüft ob der Warenkorb sichtbar ist
     */
    public boolean isCartVisible() {
        try {
            return cartIcon.isDisplayed();
        } catch (Exception e) {
            return isElementPresent(By.xpath("//div[contains(@class, 'cart')]"));
        }
    }
}
