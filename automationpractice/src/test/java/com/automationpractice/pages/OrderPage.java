/**
 * This is order page class. It has all the element present in the order page & all possible action in the order page.
 * 
 * @since Oct 25, 2020
 */
package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.util.WebUtil;

public class OrderPage extends WebUtil {

	WebDriver driver;

	@FindBy(xpath = "//*[@id='center_column']//p[@class='cart_navigation clearfix']//span")
	public WebElement proced_to_checkout;
	@FindBy(id = "cgv")
	public WebElement terms_checkBox;
	@FindBy(xpath = "//*[@id='center_column']//p[@class='payment_module']/a[@title='Pay by bank wire']")
	public WebElement pay_by_bank_wire;
	@FindBy(xpath = "//*[@id='center_column']//p[@class='cart_navigation clearfix']//span[text()='I confirm my order']")
	public WebElement confirm_order;
	@FindBy(xpath = "//*[@id='center_column']/div/p/strong")
	public WebElement order_Confirmation_msg;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This function will proceed for checkout
	 */
	public void proceed_to_Checkout() {
		try {
			System.out.println("Proceed to checkout");
			click(proced_to_checkout);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This function will agree term of service
	 */
	public void agreed_terms_of_service() {
		try {
			System.out.println("Agreed terms of service");
			Thread.sleep(4000);
			terms_checkBox.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This function will do the payment
	 */
	public void do_payment() {
		try {
			click(pay_by_bank_wire);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This function will confirm the order
	 */
	public void do_confirm_order() {
		try {
			click(confirm_order);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This function will return confirmation message
	 * 
	 * @return confirm_msg - String - Confirmation message
	 */
	public String get_oder_confirmation_msg() {
		try {
			return wait_for_element_visible(order_Confirmation_msg).getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
