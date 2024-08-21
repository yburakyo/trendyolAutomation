package com.ybydev;


import com.ybydev.pages.*;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private WebDriver driver;
    private EdgeOptions options;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchResultPage searchResultPage;
    private ProductPage productPage;
    private CartPage cartPage;

    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    @Given("user opened trendyol.com on their browser")
    public void userOpenedTrendyolComOnTheirBrowser() {
        options = new EdgeOptions();
        HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
        edgePrefs.put("profile.default_content_setting_values.notifications", 2); // 2 is block
        options.setExperimentalOption("prefs", edgePrefs);

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com/");
    }

    @When("the main page opens properly")
    public void theMainPageOpensProperly() {
        homePage = new HomePage(driver);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean isDocumentReady = (Boolean) jsExecutor.executeScript("return document.readyState === 'complete';");

        assertTrue(isDocumentReady, "Page did not load.");
    }


    @And("user attempts to login with wrong credentials twice")
    public void userAttemptsToLoginWithWrongCredentialsTwice() {
        homePage.tryCloseGenderPrefModal();

        loginPage = homePage.goToLoginPage();
        loginPage.enterEmail("asdas@gmail.com");
        loginPage.enterPassword("sadadasd");
        loginPage.login();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        loginPage.enterEmail("bvbvbvb@gmail.com");
        loginPage.enterPassword("lklklklk");
        loginPage.login();
    }

    @And("user searches for {string} in the searchbox")
    public void userSearchesForKeywordInTheSearchbox(String arg0) {
        loginPage.enterSearchTerm();

        searchResultPage = loginPage.goToSearchResultPage();
    }

    @And("user clicks a random product from results")
    public void userClicksARandomProductFromResults() {
        productPage = searchResultPage.goToProductPage();
    }

    @And("user adds the product to cart")
    public void userAddsTheProductToCart() {
        productPage.closeAnladimModal();

        productPage.addToCart();
        cartPage = productPage.goToCartPage();
    }

    @Then("the price of the product before adding to the cart should be the same after adding it to the cart")
    public void thePriceOfTheProductBeforeAddingToTheCartShouldBeTheSameAfterAddingItToTheCart() {
        cartPage.tryCloseExtendWarrantyModal();

        assertEquals(productPage.getProductPriceOnListing(), cartPage.getProductPriceInCart(),
                "Product price is different before and after adding to cart.");
    }

    @When("user increases the number of products in the basket to two")
    public void userIncreasesTheNumberOfProductsInTheBasketToTwo() {
        cartPage.incrementProductQuantity();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    @Then("the product quantity should be 2 in the basket")
    public void theProductQuantityShouldBeInTheBasket() {
        assertEquals("2", cartPage.getProductQuantity(), "Product quantity in the cart is not 2.");
    }

    @When("user deletes all products from the basket")
    public void userDeletesAllProductsFromTheBasket() {
        cartPage.deleteProduct();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    @Then("the basket should be empty")
    public void theBasketShouldBeEmpty() {
        try {
            if (Objects.equals(cartPage.getProductPriceInCart(), null)) {
                fail("Cart is not empty.");
            }
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}