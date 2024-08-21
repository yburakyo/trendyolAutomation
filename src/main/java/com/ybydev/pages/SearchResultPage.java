package com.ybydev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SearchResultPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected List<WebElement> searchResultList;
    protected WebElement searchResult;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        searchResultList = driver.findElements(By.className("p-card-wrppr"));
        searchResult = searchResultList.get(new Random().nextInt(searchResultList.size()));
    }

    public ProductPage goToProductPage() {
        wait.until(ExpectedConditions.elementToBeClickable(searchResult));
        searchResult.click();
        return new ProductPage(driver);
    }

}
