# Author : Andhiny Fatikha Rizki
# Feature: Login Functionality
# Creation Date : 2 November 2025

@LoginFunctionality
Feature: Login Feature
As a user 
In order to access my account 
I want to be able to log in with credentials

  @01_LoginValidCredentials
  Scenario Outline: [Positive] Login with valid email and password
    Given the user opens the Saucedemo application 
    When the user enters credentials <USERNAME>, <PASSWORD>
		And the user clicks the Login button 
		Then the user should be redirected to the homepage <IS_VALID>
		And the user closes the application
	
	Examples:
		| USERNAME 				| PASSWORD | IS_VALID |
		| bod@example.com	| 10203040 | YES			|
	
	@02_LoginInvalidCredentials
	Scenario Outline: [Negative] Login with invalid email and password 
		Given the user opens the Saucedemo application 
		When the user enters credentials <USERNAME>, <PASSWORD> 
		And the user clicks the Login button 
		Then the user should be redirected to the homepage <IS_VALID>
		And the user closes the application
		
	Examples:
		| USERNAME 							| PASSWORD | IS_VALID |
		| miyako@mailnesia.com	| asal123  | No			  |