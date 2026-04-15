Feature: Checkout process on Sauce Demo

  Background:
    Given Alice is on the SauceDemo login page

  @checkout
  Scenario: Complete full checkout and verify order confirmation
    When she logs in with username "standard_user" and password "secret_sauce"
    And she adds the product "Sauce Labs Backpack" to the cart
    And she adds the product "Sauce Labs Bike Light" to the cart
    And she proceeds to checkout with first name "Juan", last name "Perez" and postal code "15001"
    And she verifies the order summary shows item total "Item total: $37.99"
    And she completes the order
    Then she should see the confirmation message "Thank you for your order!"

  @checkout
  Scenario: Checkout fails with empty first name
    When she logs in with username "standard_user" and password "secret_sauce"
    And she adds the product "Sauce Labs Backpack" to the cart
    And she proceeds to checkout with first name "", last name "Perez" and postal code "15001"
    Then she should see the checkout error message "Error: First Name is required"
    And the checkout form should remain visible