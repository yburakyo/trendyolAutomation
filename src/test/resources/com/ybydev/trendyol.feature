Feature: Trendyol Automation
  Trendyol automation tests, referencing the "QA HOMEWORK.pdf" file, assigned by Bitpace

  Scenario: Has the main page opened properly?
    Given user opened trendyol.com on their browser
    Then the main page opens properly

  Scenario: Is the price of the product before adding to the cart the same as the price after adding it to the cart?
    Given user opened trendyol.com on their browser
    When the main page opens properly
    And user attempts to login with wrong credentials twice
    And user searches for keyword in the searchbox
    And user clicks a random product from results
    And user adds the product to cart
    Then the price of the product before adding to the cart should be the same after adding it to the cart

  Scenario: Has the quantity of the product in the cart increased after clicking the increment button?
    Given user opened trendyol.com on their browser
    When the main page opens properly
    And user attempts to login with wrong credentials twice
    And user searches for keyword in the searchbox
    And user clicks a random product from results
    And user adds the product to cart
    And user increases the number of products in the basket to two
    Then the product quantity should be 2 in the basket

  Scenario: Is the basket empty after deleting all the products from the basket?
    Given user opened trendyol.com on their browser
    When the main page opens properly
    And user attempts to login with wrong credentials twice
    And user searches for keyword in the searchbox
    And user clicks a random product from results
    And user adds the product to cart
    And user deletes all products from the basket
    Then the basket should be empty