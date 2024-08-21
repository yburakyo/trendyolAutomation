package com.ybydev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected WebElement emailField;
    protected WebElement passwordField;
    protected WebElement loginBtn;
    protected WebElement searchField; // a bit counterintuitive for me, but the tests do not need to leave the LoginPage
                                      // to search for "Laptop" keyword, so i will implement the goToSearchResultsPage
                                      // here, in the LoginPage.
    protected WebElement searchBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        emailField = driver.findElement(By.id("login-email"));
        passwordField = driver.findElement(By.id("login-password-input"));
        loginBtn = driver.findElement(By.cssSelector(".q-primary.q-fluid.q-button-medium.q-button.submit"));
        searchField = driver.findElement(By.cssSelector(
                "input[placeholder='Aradığınız ürün, kategori veya markayı yazınız']"));
        searchBtn = driver.findElement(By.className("cyrzo7gC"));
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void login() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }

    public void enterSearchTerm() {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys("laptop standı");
    }

    public SearchResultPage goToSearchResultPage() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
        return new SearchResultPage(driver);
    }
}
