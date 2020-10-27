/**
 * This is Home page class. It has all the element present in the Home page and all possible actions in home page.
 * 
 * @since Oct 25, 2020
 */
package com.automationpractice.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.util.Utilities;
import com.automationpractice.util.WebUtil;

public class HomePage extends WebUtil {
	WebDriver driver;

	@FindBy(id = "search_query_top")
	public WebElement search;
	@FindBy(xpath = "//*[@id='center_column']//div[@class='right-block']//div[@class='content_price']/span[@class='price product-price']")
	public List<WebElement> productPrices;
	@FindBy(xpath = "//*[@id='center_column']//following::a[@class='product-name']")
	List<WebElement> productNames;
	@FindBy(xpath = "//*[@id='center_column']//div[@class='right-block']//span/span")
	List<WebElement> productInStock;
	@FindBy(xpath = "//*[@id='layer_cart']//div[@class='button-container']/a[@title='Proceed to checkout']")
	public WebElement checkOut;
	@FindBy(xpath = "//span[contains(text(), 'results have been found')]")
	public WebElement search_element_found;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This Function will search a product
	 * 
	 * @param productName
	 *            - String - product name which need to be search
	 */
	public void searchProduct(String productName) {
		try {
			System.out.println("Search Product...");
			search.sendKeys(productName);
			search.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This function will get all the product info from search result.It will get
	 * item no, product name & price.
	 * 
	 * @return product_name_price_map - HashMap - product info
	 *         (itemNo_productname_productAvailability, productPrice) from search
	 *         result.
	 */
	public HashMap<String, Double> get_Searched_product_Details() {
		try {
			wait_for_element_visible(search_element_found);
			HashMap<String, Double> product_name_price_map = new HashMap<String, Double>();
			for (int i = 0; i < productNames.size(); i++) {
				String product_name = productNames.get(i).getText();
				String product_availability = productInStock.get(i).getText();
				double product_price = Double.parseDouble(productPrices.get(i).getText().replace("$", ""));
				product_name_price_map.put((i + 1) + "_" + product_name + "_" + product_availability, product_price);
			}
			return product_name_price_map;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * This function will return item no for a lowest cost item
	 * 
	 * @param product_name_price_map
	 *            - HashMap - All product info from search result
	 * 
	 * @return lowest_cost_item_no - int - Lowest cost item no
	 */
	public int get_lowestCost_item(HashMap<String, Double> product_name_price_map) {
		try {
			int lowest_cost_item_no = 0;
			HashMap<String, Double> sorted_item = new LinkedHashMap<String, Double>();
			sorted_item = Utilities.sort_map_by_value(product_name_price_map);
			for (Map.Entry<String, Double> hm : sorted_item.entrySet()) {
				String product_info = hm.getKey();
				String[] details = product_info.split("_");
				if (details[2].equalsIgnoreCase("In Stock")) {
					lowest_cost_item_no = Integer.parseInt(details[0]);
					break;
				}
			}
			return lowest_cost_item_no;
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	/**
	 * This function will add item to cart
	 * 
	 * @param itemNo
	 *            - int - item no which need to add in cart
	 */
	public void add_to_cart(int item_no) {
		try {
			System.out.println("Add to cart");
			WebElement product_item = driver.findElement(By.xpath("//*[@id='center_column']/ul/li[" + item_no + "]"));
			WebElement add_to_cart = driver.findElement(By.xpath("//*[@id='center_column']//li[" + item_no
					+ "]//div[@class='right-block']//div[@class='button-container']/a[1]/span"));
			Actions builder = new Actions(driver);
			Action add_to_cart_actions = builder
					.moveToElement(product_item)
					.moveToElement(add_to_cart)
					.click()
					.build();
			add_to_cart_actions.perform();
			click(checkOut);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
