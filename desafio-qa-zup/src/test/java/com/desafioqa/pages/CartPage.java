package com.desafioqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.desafioqa.hooks.ShoppingCartHooks.driver;

public class CartPage extends BasePage{
    private static String itemNameLocator = "//h4[contains(text(), '%s')]";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemOnCart(String itemName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String locator = String.format(itemNameLocator, itemName);
        WebElement element = wait.ignoring(TimeoutException.class).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        return element != null;
    }
}
