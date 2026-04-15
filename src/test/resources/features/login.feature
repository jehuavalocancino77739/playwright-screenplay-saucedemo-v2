Feature: Login on Sauce Demo

  Background:
    Given Alice is on the SauceDemo login page

  @login
  Scenario: Successful login with valid credentials
    When she logs in with username "standard_user" and password "secret_sauce"
    Then she should be redirect to the inventory page
    And she should see the page title "Products"

  @login @negative
  Scenario: Failed login with a locked out user
    When she logs in with username "locked_out_user" and password "secret_sauce"
    Then she should see the error message "Epic sadface: Sorry, this user has been locked out."
    And she should remain on the login page

  @login @advanced
  Scenario: Performance glitch user login takes more than 3 seconds
    When she logs in with username "performance_glitch_user" and password "secret_sauce"
    Then she should be redirect to the inventory page
    And the page load time should be greater than 3000 milliseconds

  @login @advanced
  Scenario: Verify session persistence after login
    When she logs in with username "standard_user" and password "secret_sauce"
    And she navigates to the cart page
    And she navigates back to the inventory page
    Then she should still be logged in
    And she should see the page title "Products"