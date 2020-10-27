/**
 * This class has utility functions for web page interactions
 * 
 * @since Oct 25, 2020
 */
package com.automationpractice.util;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WebUtil extends CommonUtil {

	static FluentWait<WebDriver> wait = null;

	/**
	 * This Function will return a fluent wait for a given max wait time
	 * 
	 * @param max_wait_time
	 *            - int - max wait time
	 * 
	 * @return - FluentWait - return wait instance
	 */
	public static FluentWait<WebDriver> get_wait(int max_wait_time) {
		try {
			wait = new FluentWait<WebDriver>(driver);
			wait.withTimeout(Duration.ofSeconds(max_wait_time));
			wait.pollingEvery(Duration.ofMillis(50));
			wait.ignoring(NoSuchElementException.class);
			return wait;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function will wail for element for click
	 * 
	 * @param element
	 *            - WebElement - Element for need to click
	 * 
	 * @return - webElement - return element after found
	 */
	public static WebElement wait_for_click(WebElement element) {
		try {
			wait = get_wait(15);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function will wait for element to be visible
	 * 
	 * @param element
	 *            - WebElement - Element to be visible
	 * 
	 * @return - webElement - return element after visible
	 */
	public static WebElement wait_for_element_visible(WebElement element) {
		try {
			wait = get_wait(15);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function will click on a element when element is ready for clickable
	 * 
	 * @param element
	 *            - WebElement - Element to be click
	 */
	public static void click(WebElement element) {
		try {
			wait_for_click(element).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will enter text in a element
	 * 
	 * @param element
	 *            - WebElement - element where text need to be entered
	 * @param enteredText
	 *            - string - Text to be entered
	 */
	public static void sendKeys(WebElement element, String enteredText) {
		try {
			wait_for_element_visible(element).sendKeys(enteredText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
