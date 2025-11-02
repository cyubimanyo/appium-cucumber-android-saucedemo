# Author : Andhiny Fatikha Rizki
# Feature: Rating Functionality
# Creation Date : 2 November 2025

@RatingFunctionality
Feature: Rating Functionality
As a user
In order to rate the product
I want to be able to give rating to the product

  @01_GiveRatingToProduct
  Scenario Outline: [Positive] Give rating to the product
    Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button
		And the user gives a five star rating to the product
		Then the product should display the submission popup message
		And the user closes the application
	
	Examples:
		| USERNAME 				| PASSWORD |
		| bod@example.com	| 10203040 |