package cart_feature
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



class cart {

	@And("the user selects the product")
	def selectProduct() {

		Mobile.waitForElementPresent(findTestObject('Object Repository/CartPage/Old/select_product_1'), 2)
		KeywordUtil.logInfo("Berhasil pilih Product 1")
		Mobile.tap(findTestObject('Object Repository/CartPage/Old/select_product_1'), 2)
		Mobile.takeScreenshot()

		boolean isVisible = Mobile.verifyElementVisible(findTestObject('Object Repository/CartPage/Old/selected_product'), 2, FailureHandling.OPTIONAL)

		if (!isVisible) {
			KeywordUtil.logInfo("Product berhasil dipilih")
			Mobile.takeScreenshot()
			return true
		} else {
			KeywordUtil.logInfo("Product gagal dipilih")
			Mobile.takeScreenshot()
			return false
		}
	}

	@And("the user clicks the Add To Cart button")
	def clickAddToCartButton() {
	    Mobile.scrollToText('Add to cart', FailureHandling.OPTIONAL)
	
	    TestObject addToCartBtn = findTestObject('Object Repository/CartPage/Old/btn_add_to_cart')
	
	    boolean isPresent = Mobile.waitForElementPresent(addToCartBtn, 3, FailureHandling.OPTIONAL)
	
	    if (isPresent) {
	        boolean isEnabled = Mobile.verifyElementAttributeValue(addToCartBtn, 'enabled', 'true', 1, FailureHandling.OPTIONAL)
	        boolean isClickable = Mobile.verifyElementAttributeValue(addToCartBtn, 'clickable', 'true', 1, FailureHandling.OPTIONAL)
	
	        if (isEnabled && isClickable) {
	            Mobile.tap(addToCartBtn, 2)
	            Mobile.takeScreenshot()
	            KeywordUtil.logInfo("✅ Tombol 'Add to cart' clickable dan berhasil di-tap (Positive Case)")
	        } else {
	            KeywordUtil.logInfo("⚠️ Tombol 'Add to cart' ditemukan tapi tidak clickable/enabled (Negative Case berhasil)")
	            Mobile.takeScreenshot()
	        }
	    } else {
	        KeywordUtil.markWarning("🚫 Tombol 'Add to cart' tidak ditemukan di layar")
	        Mobile.takeScreenshot()
	    }
	}

	@And("the user clicks Remove Item button")
	def clickRemoveItemButton() {
		int maxRetry = 5
		int attempt = 0
		boolean itemRemoved = false

		while (attempt < maxRetry && !itemRemoved) {
			try {
				Mobile.waitForElementPresent(findTestObject('Object Repository/CartPage/Old/btn_remove_item'), 2)
				Mobile.tap(findTestObject('Object Repository/CartPage/Old/btn_remove_item'), 2)
				Mobile.takeScreenshot()

				itemRemoved = !Mobile.verifyElementVisible(findTestObject('Object Repository/CartPage/Old/value_text_product'), 2, FailureHandling.OPTIONAL)

				if (!itemRemoved) {
					KeywordUtil.logInfo("Item masih ada, retry klik Remove Item")
				}
			} catch(Exception e) {
				KeywordUtil.logInfo("Gagal klik Remove Item: " + e.getMessage())
			}
			attempt++
		}

		if (itemRemoved) {
			KeywordUtil.logInfo("Item berhasil dihapus dari cart")
		} else {
			KeywordUtil.markFailed("Gagal menghapus item setelah " + maxRetry + " percobaan")
		}
	}

	@And("the user clicks the cart icon")
	def clickCartIcon() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/CartPage/Old/btn_view_cart'), 2)
		Mobile.tap(findTestObject('Object Repository/CartPage/Old/btn_view_cart'), 2)
		Mobile.takeScreenshot()
	}
	
	@And("the user click decrease quantity")
	def clickDecreaseQuantity() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/CheckoutPage/btn_decrease_quantity'), 2)
		Mobile.tap(findTestObject('Object Repository/CheckoutPage/btn_decrease_quantity'), 2)
		Mobile.takeScreenshot()
	}

	@Then("the product should be added to the cart (.*), (.*)")
	def viewAddToCart(String IS_VALID, String PRODUCT_NAME) {

		if (IS_VALID.contains("Yes")) {
			Mobile.waitForElementPresent(findTestObject('Object Repository/CartPage/Old/value_text_product'), 2)
			Mobile.verifyElementText(findTestObject('Object Repository/CartPage/Old/value_text_product'), PRODUCT_NAME, FailureHandling.OPTIONAL)
			Mobile.takeScreenshot()
			KeywordUtil.logInfo("[POSITIVE CASE] Berhasil Add Product to Cart")
		} else {
			boolean isVisible = Mobile.verifyElementText(findTestObject('Object Repository/CartPage/Old/value_text_product'), PRODUCT_NAME, FailureHandling.OPTIONAL)

			if (!isVisible) {
				KeywordUtil.logInfo("[NEGATIVE CASE] Tidak ada Product Item di Cart")
				Mobile.verifyElementVisible(findTestObject('Object Repository/CartPage/Old/value_text_no_item'), 2)
				Mobile.verifyElementVisible(findTestObject('Object Repository/CartPage/Old/btn_go_shopping'), 2)
				Mobile.takeScreenshot()
				return false
			} else {
				KeywordUtil.logInfo("Element Product terlihat, Negative Case gagal")
				Mobile.takeScreenshot()
				return true
			}
		}
	}
}