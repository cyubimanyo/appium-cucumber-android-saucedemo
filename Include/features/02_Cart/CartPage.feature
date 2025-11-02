# Author : Andhiny Fatikha Rizki
# Feature: Cart Functionality
# Creation Date : 2 November 2025

@CartFunctionality
Feature: Cart Feature
As a user 
In order to purchase items 
I want to be able to add product to the cart

  @01_AddProductToTheCart
  Scenario Outline: [Positive] Add product to the cart
  	Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product 
		And the user clicks the Add To Cart button
		And the user clicks the cart icon 
		Then the product should be added to the cart <IS_VALID>, <PRODUCT_NAME> 
		And the user closes the application
		
    Examples:
			| USERNAME 				| PASSWORD | IS_VALID | PRODUCT_NAME 				|
			| bod@example.com	| 10203040 | YES			| Sauce Labs Backpack |
	
	@02_RemoveProductFromTheCart
	Scenario Outline: [Positive] Remove product from the cart 
		Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product 
		And the user clicks the Add To Cart button
		And the user clicks the cart icon
		And the user clicks Remove Item button 
		Then the product should be added to the cart <IS_VALID>, <PRODUCT_NAME> 
		And the user closes the application
		
    Examples:
			| USERNAME 				| PASSWORD | IS_VALID | PRODUCT_NAME 				|
			| bod@example.com	| 10203040 | NO			  | Sauce Labs Backpack |
	
	@03_PreventCheckoutWithEmptyCart
	Scenario Outline: [Negative] Prevent checkout with empty cart
		Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
    And the user clicks the cart icon
    Then the product should be added to the cart <IS_VALID>, <PRODUCT_NAME>
    And the user closes the application
    
    Examples:
			| USERNAME 				| PASSWORD | IS_VALID | PRODUCT_NAME 				|
			| bod@example.com	| 10203040 | NO			  | Sauce Labs Backpack |
	