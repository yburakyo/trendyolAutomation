package com.ybydev.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected WebElement initialLoginBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        initialLoginBtn = driver.findElement(By.className("account-user"));
    }

    public void tryCloseGenderPrefModal() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));

            WebElement closeGenderPrefModal;
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-close")));
            closeGenderPrefModal = driver.findElement(By.className("modal-close"));
            closeGenderPrefModal.click();
        } catch (TimeoutException e) {
            System.out.println("No gender preference modal, proceeding.");
        }
    }

    public LoginPage goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(initialLoginBtn));
        initialLoginBtn.click();
        return new LoginPage(driver);
    }
}
