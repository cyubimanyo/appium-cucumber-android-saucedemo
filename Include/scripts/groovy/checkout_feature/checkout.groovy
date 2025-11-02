package checkout_feature
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class checkout {
	@And("the user clicks the Proceed To Checkout button")
	def clickProceedToCheckoutButton() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/btn_proceed_to_checkout'), 2)
		Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_proceed_to_checkout'), 2)
		Mobile.takeScreenshot()
	}

	@And("the user fills the shipping details (.*), (.*), (.*), (.*), (.*)")
	def fillTheShippingDetails(String FULL_NAME, String ADDRESS, String CITY, String ZIP_CODE, String COUNTRY) {
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_full_name_shipping'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_full_name_shipping'), FULL_NAME, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_address'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_address'), ADDRESS, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_city'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_city'), CITY, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_zip_code'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_zip_code'), ZIP_CODE, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_country'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_country'), COUNTRY, 10)

		Mobile.takeScreenshot()
	}

	@And("the user clicks the To Payment button")
	def clickToPaymentButton() {
		Mobile.scrollToText('To Payment', FailureHandling.OPTIONAL)
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/btn_to_payment'), 2)
		Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_to_payment'), 2)
		Mobile.takeScreenshot()
	}

	@And("the user fills the payment method (.*), (.*), (.*), (.*)")
	def fillThePaymentMethod(String FULL_NAME, String CARD_NUMBER, String EXPIRE_DATE, String SECURITY_CODE) {
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_full_name_payment'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_full_name_payment'), FULL_NAME, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_card_number'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_card_number'), CARD_NUMBER, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_expire_date'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_expire_date'), EXPIRE_DATE, 10)

		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/input_security_code'), 10)
		Mobile.setText(findTestObject('Object Repository/CheckoutPage/input_security_code'), SECURITY_CODE, 10)

		Mobile.takeScreenshot()
	}

	@And("the user clicks the Review Order button")
	def clickReviewOrderButton() {
		Mobile.scrollToText('Review Order', FailureHandling.OPTIONAL)
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/btn_review_order'), 2)
		Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_review_order'), 2)
		Mobile.takeScreenshot()
	}

	@And("the user clicks the Place Order button")
	def clickPlaceOrderButton() {
		Mobile.scrollToText('Place Order', FailureHandling.OPTIONAL)
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/btn_place_order'), 2)
		Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_place_order'), 2)
		Mobile.takeScreenshot()
	}

	@And("the product should be successfully checked out from the cart")
	def verifyAfterCheckout() {
		boolean isVisible = Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/text_checkout_complete'), 2, FailureHandling.OPTIONAL)

		if (!isVisible) {
			KeywordUtil.logInfo("Element tidak terlihat, gagal Checkout")
			Mobile.takeScreenshot()
			return false
		} else {
			KeywordUtil.logInfo("Element terlihat, klik Continue Shopping button")
			Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_continue_shopping'), 2)
			Mobile.takeScreenshot()
			return true
		}
	}
	
	@And("the error message should appear indicating that all required fields must be filled (.*)")
	def verifyErrorRequiredFields(String TYPE) {
		
		if (TYPE.contains("Shipping")) {
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_full_name_shipping'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_full_name_shipping'), "Please provide your full name")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_address'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_address'), "Please provide your address")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_city'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_city'), "Please provide your city")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_zip_code'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_zip_code'), "Please provide your zip")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_country'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_country'), "Please provide your")
			
			Mobile.takeScreenshot()
		} else {
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_full_name_payment'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_full_name_payment'), "Value looks invalid")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_expire_date'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_expire_date'), "Value looks invalid")
			
			Mobile.verifyElementVisible(findTestObject('Object Repository/CheckoutPage/error_security_code'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CheckoutPage/error_security_code'), "Value looks invalid")
			
			Mobile.takeScreenshot()
		}
	}
}