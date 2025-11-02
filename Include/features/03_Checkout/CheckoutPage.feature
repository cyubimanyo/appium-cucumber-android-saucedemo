# Author : Andhiny Fatikha Rizki
# Feature: Checkout Functionality
# Creation Date : 2 November 2025

@CheckoutFunctionality
Feature: Checkout Feature
As a user
In order to shopping
I want to be able to checkout product

  @01_CheckoutProductFromTheCart
  Scenario Outline: [Positive] Checkout product from the cart
    Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product 
		And the user clicks the Add To Cart button
		And the user clicks the cart icon
		And the user clicks the Proceed To Checkout button
		And the user fills the shipping details <FULL_NAME>, <ADDRESS>, <CITY>, <ZIP_CODE>, <COUNTRY>
		And the user clicks the To Payment button
		And the user fills the payment method <FULL_NAME>, <CARD_NUMBER>, <EXPIRE_DATE>, <SECURITY_CODE>
		And the user clicks the Review Order button 
		And the user clicks the Place Order button
		Then the product should be successfully checked out from the cart
		And the user closes the application 
		
		
    Examples:
			| USERNAME 				| PASSWORD | IS_VALID | FULL_NAME 		 | ADDRESS 			 | CITY  | ZIP_CODE | COUNTRY | CARD_NUMBER | EXPIRE_DATE | SECURITY_CODE |
			| bod@example.com	| 10203040 | YES			| Rebecca Winter | Mandorley 112 | Truro | 89750    | UK      | 55555555555 | 1111				| 123						|
	
	@02_PreventAddingProductWithZeroQuantity
	Scenario Outline: [Negative] Prevent adding product with zero quantity
		Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product
		And the user click decrease quantity
		And the user clicks the Add To Cart button
		Then the user closes the application
		
		Examples:
			| USERNAME 				| PASSWORD |
			| bod@example.com	| 10203040 |
	
	@03_PreventFieldEmptyAtShippingDetails
	Scenario Outline: [Negative] Prevent field empty at shipping details
		Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product 
		And the user clicks the Add To Cart button
		And the user clicks the cart icon
		And the user clicks the Proceed To Checkout button
		And the user clicks the To Payment button
		Then the error message should appear indicating that all required fields must be filled <TYPE>
		And the user closes the application
		
		Examples:
			| USERNAME 				| PASSWORD | TYPE 		|	
			| bod@example.com	| 10203040 | Shipping |
	
	@04_PreventFieldEmptyAtPaymentMethodDetails
	Scenario Outline: [Negative] Prevent field empty at payment method details
		Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user selects the product 
		And the user clicks the Add To Cart button
		And the user clicks the cart icon
		And the user clicks the Proceed To Checkout button
		And the user fills the shipping details <FULL_NAME>, <ADDRESS>, <CITY>, <ZIP_CODE>, <COUNTRY>
		And the user clicks the To Payment button
		Then the error message should appear indicating that all required fields must be filled <TYPE>
		And the user closes the application
		
		Examples:
			| USERNAME 				| PASSWORD | TYPE 	 | FULL_NAME 		 	| ADDRESS 			 | CITY  | ZIP_CODE | COUNTRY |
			| bod@example.com	| 10203040 | Payment | Rebecca Winter | Mandorley 112  | Truro | 89750    | UK      |