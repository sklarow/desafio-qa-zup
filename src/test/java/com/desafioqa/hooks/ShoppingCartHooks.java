package com.desafioqa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShoppingCartHooks {
    public static WebDriver driver=null;

    @Before
    public void BrowserSetup(){
        System.setProperty("webdriver.chrome.driver", "/env/drivers/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @After
    public void BrowserTearDown() throws InterruptedException {
        driver.quit();
    }

}
