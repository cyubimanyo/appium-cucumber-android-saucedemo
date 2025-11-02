package login_feature
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
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



class login {

	@Given("the user opens the Saucedemo application")
	def openApp() {
		Mobile.startApplication(RunConfiguration.getProjectDir() + '/my-demo-app.apk', false)
	}

	@When("the user enters credentials (.*), (.*)")
	def inputCredentials(String USERNAME, String PASSWORD) {
		try {
			Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/btn_view_menu'), 2)
			Mobile.tap(findTestObject('Object Repository/LoginPage/btn_view_menu'), 2)
			Mobile.takeScreenshot()

			Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/btn_login_menu'), 2)
			Mobile.tap(findTestObject('Object Repository/LoginPage/btn_login_menu'), 2)
			Mobile.takeScreenshot()

			Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/input_username'), 10)
			Mobile.setText(findTestObject('Object Repository/LoginPage/input_username'), USERNAME, 10)
			Mobile.takeScreenshot()

			Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/input_password'), 10)
			Mobile.setText(findTestObject('Object Repository/LoginPage/input_password'), PASSWORD, 10)
			Mobile.takeScreenshot()
		} catch(Exception e) {
			KeywordUtil.logInfo("Failed to enter credentials: " + e.getMessage())
			Mobile.closeApplication()
			openApp()
			inputCredentials(USERNAME, PASSWORD)
		}
	}

	@And("the user clicks the Login button")
	def clickLoginButton() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/LoginPage/btn_login'), 2)
		Mobile.tap(findTestObject('Object Repository/LoginPage/btn_login'), 2)
		Mobile.takeScreenshot()
	}

	@Then("the user should be redirected to the homepage (.*)")
	def directToHomePage(String IS_VALID) {
		if (IS_VALID.contains("Yes")) {
			Mobile.verifyElementVisible(findTestObject('Object Repository/LoginPage/btn_view_menu'), 2)
			Mobile.tap(findTestObject('Object Repository/LoginPage/btn_view_menu'), 2)
			Mobile.takeScreenshot()
			KeywordUtil.logInfo("Berhasil diarahkan ke homepage (Positive Case)")
		} else {
			boolean isVisible = Mobile.verifyElementVisible(findTestObject('Object Repository/LoginPage/btn_view_menu'), 2, FailureHandling.OPTIONAL)

			if (!isVisible) {
				KeywordUtil.logInfo("Element tidak terlihat, Negative Case berhasil")
				Mobile.takeScreenshot()
				return false
			} else {
				KeywordUtil.logInfo("Element terlihat, Negative Case gagal")
				Mobile.takeScreenshot()
				return true
			}
		}
	}
}