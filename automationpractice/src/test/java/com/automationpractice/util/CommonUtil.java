/**
 * This class has all common utility functions 
 * 
 *  @since Oct 25, 2020
 */
package com.automationpractice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.OrderPage;

public class CommonUtil {

	// Page Object
	public HomePage homePage;
	public LoginPage loginPage;
	public OrderPage orderPage;

	// Path variables
	static String projectPath = System.getProperty("user.dir");;
	static String resourcePath = projectPath + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator;
	static String configFileName = resourcePath + "ConfigFiles" + File.separator + "EnvironmentConfig.conf";

	// Properties Variable
	public static String appUrl = "";
	public static String userName = "";
	public static String password = "";
	public static String browserName = "";
	static WebDriver driver = null;

	// Class Variable
	static Row row = null;
	static Row currentRow = null;
	static Map<String, Integer> headerMap = new HashMap<String, Integer>();

	/**
	 * This Function will read configuration values from configuration file
	 * 
	 * @throws IOException
	 */
	public static void initiate_Configuration() throws IOException {
		Properties prop = new Properties();
		InputStream configFile = new FileInputStream(configFileName);
		prop.load(configFile);
		appUrl = prop.getProperty("AppUrl");
		userName = prop.getProperty("UserName");
		password = prop.getProperty("Password");
		browserName = prop.getProperty("BrowserName");
	}

	/**
	 * This Function will invoke browser based on configuration
	 * 
	 * @return - WebDriver - driver -- Return invoked driver instance
	 */
	public WebDriver initiate_Driver() {
		try {
			initiate_Configuration();
			if (browserName.equalsIgnoreCase("chrome")) {
				System.out.println("Invoke Chrome browser");
				System.setProperty("webdriver.chrome.driver",
						resourcePath + "Drivers" + File.separator + "chromedriver.exe");
				driver = new ChromeDriver();
			}
			if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						resourcePath + "Drivers" + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * This function will initiate page element from pages
	 */
	public void initiate_Pages() {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			orderPage = new OrderPage(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will open the application
	 */
	public void open_App() {
		try {
			System.out.println("Open Application");
			initiate_Driver();
			initiate_Pages();
			driver.get(appUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will login to the application with configured user in
	 * configuration file
	 */
	public void login_To_App() {
		try {
			System.out.println("Login to the Application");
			loginPage.login(userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Function will read xls data from TestData xls file.
	 * 
	 * @param rowNum
	 *            - int - Row number need to read
	 * @param fileName
	 *            - String - File name need to read
	 * 
	 * @throws IOException
	 */
	public static void read_Xl_Data(int rowNum, String fileName) throws IOException {
		XSSFWorkbook workbook = null;
		try {
			String filePath = resourcePath + File.separator + "TestData" + File.separator + fileName;
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			row = sheet.getRow(0);
			int firstColNum = row.getFirstCellNum();
			int lastColnum = row.getLastCellNum();
			for (int i = firstColNum; i < lastColnum; i++) {
				Cell cell = row.getCell(i);
				headerMap.put(cell.getStringCellValue(), cell.getColumnIndex());
			}
			currentRow = sheet.getRow(rowNum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	/**
	 * This function will return value from xls doc based on column header
	 * 
	 * @param colName
	 *            - String - Column header name
	 * 
	 * @return - String - Return column value
	 */
	public static String get_Xl_Value(String colName) {
		try {
			int colIndex = headerMap.get(colName);
			Cell cell = currentRow.getCell(colIndex);
			return cell.getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This function will close the Applicaton
	 */
	public static void close_App() {
		driver.quit();
	}
}
