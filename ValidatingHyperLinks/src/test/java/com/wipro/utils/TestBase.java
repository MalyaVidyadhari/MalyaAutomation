package com.wipro.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	protected static WebDriver driver;
	private static String path = System.getProperty("user.dir");
	private static String driverPath = path + "\\drivers\\";

	public WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath +"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", driverPath +"geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(appURL);
		return driver;
	}

	public static void initialization(String browserType, String appURL)
	{
		setDriver(browserType, appURL);
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}


}
