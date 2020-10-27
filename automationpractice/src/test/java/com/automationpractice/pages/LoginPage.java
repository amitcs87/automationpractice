/**
 * This is Login Page class. It has all element present in the login page & all possible actions in login page
 * 
 * @since Oct 25, 2020
 */
package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.util.WebUtil;

public class LoginPage extends WebUtil {

	WebDriver driver;

	@FindBy(linkText = "Sign in")
	public WebElement signIn;
	@FindBy(id = "email")
	public WebElement email;
	@FindBy(id = "passwd")
	public WebElement pwd;
	@FindBy(id = "SubmitLogin")
	public WebElement submitLogin;
	@FindBy(xpath = "//div[@class='header_user_info']/a/span")
	public WebElement logged_user;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This function will login to the application.
	 * 
	 * @param userName
	 *            - String - user name
	 * @param passWord
	 *            - String - password
	 */
	public void login(String userName, String passWord) {
		try {
			System.out.println("Login with " + userName);
			sendKeys(email, userName);
			sendKeys(pwd, passWord);
			click(submitLogin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will return logged user name
	 * 
	 * @return - String - Return logged user name.
	 */
	public String get_logged_user() {
		try {
			return (logged_user.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
