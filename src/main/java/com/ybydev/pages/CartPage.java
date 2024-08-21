package com.ybydev.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String productPriceInCart;
    protected String productQuantity;
    protected WebElement incrementProductQuantityBtn;
    protected WebElement deleteProductBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pb-basket-item-price")));
        productPriceInCart = driver.findElement(By.cssSelector(".pb-basket-item-price")).getText();
        //TODO: span tagi icindeki fiyatı ayıkla

        incrementProductQuantityBtn = driver.findElement(By.cssSelector("[aria-label='Ürün adedi arttırma']"));
        deleteProductBtn = driver.findElement(By.cssSelector("[aria-label='Ürünü sepetten çıkartma']"));

    }

    public void tryCloseExtendWarrantyModal() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

            WebElement anladimBtn;
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//button[text()='Anladım']")));
            anladimBtn = driver.findElement(By.xpath("//button[text()='Anladım']"));
            anladimBtn.click();
        } catch (TimeoutException e) {
            System.out.println("Button 'Anladım' not found while testing.");
        }
    }

    public String getProductPriceInCart() {
        return productPriceInCart;
    }

    public void incrementProductQuantity() {
        incrementProductQuantityBtn.click();
    }

    public String getProductQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(incrementProductQuantityBtn)); // If the increment button is
                                                                                          // clickable, then the
                                                                                          // quantity field is updated
        productQuantity = driver.findElement(By.cssSelector("[aria-label='Ürün adedi']")).getAttribute("value");
        return productQuantity;
    }

    public void deleteProduct() {
        deleteProductBtn.click();
    }

}
