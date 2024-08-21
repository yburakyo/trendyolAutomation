package com.ybydev.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class ProductPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String productPriceOnListing;
    protected WebElement addToCartBtn;
    protected WebElement myCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".prc-dsc")));
        productPriceOnListing = driver.findElement(By.cssSelector(".prc-dsc")).getText();
        addToCartBtn = driver.findElement(By.cssSelector(".add-to-basket"));
        myCartBtn = driver.findElement(By.cssSelector(".link.account-basket"));
    }

    public void closeAnladimModal() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".onboarding-button")));
        driver.findElement(By.cssSelector(".onboarding-button")).click();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }

    public String getProductPriceOnListing() {
        return productPriceOnListing;
    }

    public CartPage goToCartPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".basket-preview-popup-container")));
        // The reason we check if the "basket preview popup" is visible, even though we do not interact with it, is that
        // it pops up after the item is added to basket successfully. There could be other waiting strategies but i
        // thought it was simple.

        myCartBtn.click();
        return new CartPage(driver);
    }
}
