package com.desafioqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//input[@class='sprocura']")
    private WebElement searchInput;

    @FindBy(xpath ="//input[@id='bt-busca']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void fillSearch(String itemName){
        searchInput.sendKeys(itemName);
    }

    public void clickSearch (){
        searchButton.click();
    }

}
