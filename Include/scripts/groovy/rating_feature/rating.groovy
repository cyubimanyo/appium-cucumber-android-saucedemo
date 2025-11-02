package rating_feature
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



class rating {
	@And("the user gives a five star rating to the product")
	def giveFiveStarRating() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/RatingPage/btn_five_star'), 2)
		Mobile.tap(findTestObject('Object Repository/RatingPage/btn_five_star'), 2)
		Mobile.takeScreenshot()
	}
	
	@Then("the product should display the submission popup message")
	def submissionPopupMessage() {
		String isVisible_1 = Mobile.verifyElementVisible(findTestObject('Object Repository/RatingPage/text_submit_review'), 2)
		String isVisible_2 = Mobile.verifyElementVisible(findTestObject('Object Repository/RatingPage/btn_continue'), 2)
		
		if (isVisible_1 && isVisible_2) {
			KeywordUtil.logInfo("Berhasil give rating ke product! Klik Continue")
			Mobile.tap(findTestObject('Object Repository/RatingPage/btn_continue'), 2)
			Mobile.takeScreenshot()
		} else {
			KeywordUtil.markFailed("Gagal give rating ke product!")
		}
	}
}