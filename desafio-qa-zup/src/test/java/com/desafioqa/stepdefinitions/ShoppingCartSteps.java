package com.desafioqa.stepdefinitions;

import com.desafioqa.pages.CartPage;
import com.desafioqa.pages.HomePage;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.desafioqa.hooks.ShoppingCartHooks.driver;

public class ShoppingCartSteps {

    private HomePage homePage;
    private CartPage cartPage;

    @BeforeStep
    public void setup(){
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Given("I open {string}")
    public void i_open(String url) {
        homePage.goToPage(url);
    }

    @When("I search for {string}")
    public void iSearchFor(String itemName) {
        homePage.fillSearch(itemName);
        homePage.clickSearch();
    }

    @And("I open the {string} result")
    public void iOpenTheResult(String itemName) throws InterruptedException {
        String locator = "//a[contains(text(),'" + itemName + "')]";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        element.click();
    }

    @And("I add the item to shopping cart")
    public void iAddTheItemToShoppingCart() {
        String locator = "//img[contains(@src, 'comprar_detalhes')]";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        element.click();
        String locator2 = "//span[contains(text(), 'ESTE PRODUTO FOI ADICIONADO AO CARRINHO')]";
        WebElement checkAdded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator2)));
    }

    @And("I open the shopping cart")
    public void iOpenTheShoppingCart() {
        driver.get("https://www.kabum.com.br/cgi-local/site/carrinho/carrinho.cgi");
    }

    @Then("I should see the item {string} in my shopping cart")
    public void iShouldSeeTheItemInMyShoppingCart(String itemName) {
        Assert.assertTrue(cartPage.isItemOnCart(itemName));
    }

    @Then("I should see only the item {string} in my shopping cart with quantity {int}")
    public void iShouldSeeOnlyTheItemInMyShoppingCartWithQuantity(String itemName, int quantity) {
        String locator = "//h4[contains(text(), '"+ itemName +"')]";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        String locator2 = "//p[contains(text(), 'Quantidade')]//following::div[contains(text(), '" + quantity + "')]";
        WebElement elementQty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator2)));
    }

    @And("I remove the {string} item from shopping cart")
    public void iRemoveTheItemFromShoppingCart(String itemName) {
        String locator = "//h4[contains(text(), '"+ itemName +"')]//preceding::button[contains(text(), 'Remover Produto')][1]";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        element.click();
        driver.switchTo().alert().accept();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        String locator = "//*[contains(text(), '"+ message +"')]";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    @Then("I should see the image telling me the item are not available")
    public void iShouldSeeTheImageTellingMeTheItemAreNotAvailable() {
        String locator = "//img[@alt='produto_indisponivel']";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
