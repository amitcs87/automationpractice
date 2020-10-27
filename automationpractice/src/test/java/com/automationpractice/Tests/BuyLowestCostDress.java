package com.automationpractice.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationpractice.util.CommonUtil;

public class BuyLowestCostDress extends CommonUtil{
	
	@BeforeSuite
	public void setup() {
		open_App();
	}
	
	@DataProvider (name = "data-provider")
	 public Object[][] dpMethod() throws IOException{
		read_Xl_Data(1,"SearchItemWithUser.xlsx");
		String user = get_Xl_Value("User");
		String searchItem = get_Xl_Value("Search Item");
		return new Object[][] {{ user, searchItem }};
	 }
	
	@Test(dataProvider = "data-provider" , description = "Buy lowest cost Printed Dress")
	public void buyLowestCostPrintedDress(String user, String searchItem) throws InterruptedException {
		try {
			homePage.searchProduct(searchItem);
			HashMap<String, Double> productNamePrice = homePage.get_Searched_product_Details();
			int lowest_cost_intem_no = homePage.get_lowestCost_item(productNamePrice);
			homePage.add_to_cart(lowest_cost_intem_no);
			orderPage.proceed_to_Checkout();
			login_To_App();
			Assert.assertEquals(loginPage.get_logged_user(), user);
			orderPage.proceed_to_Checkout();
			orderPage.agreed_terms_of_service();
			orderPage.proceed_to_Checkout();	
			orderPage.do_payment();
			orderPage.do_confirm_order();
			String order_confirm_msg = orderPage.get_oder_confirmation_msg();
			System.out.println(order_confirm_msg);
			Assert.assertEquals(order_confirm_msg, "Your order on My Store is complete.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void trarDown() {
		close_App();
	}

}
