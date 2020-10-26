package com.wipro.tests;

import org.testng.annotations.Test;

import com.wipro.pages.SearchPage;
import com.wipro.utils.TestBase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class VerifyHyperLinks extends TestBase {

	SearchPage searchPage;
	List<WebElement> allPageLinks;
	HttpURLConnection httpURLConnnection = null;

	@Parameters({"browserType","appURL"})
	@BeforeTest
	public void beforeTest(String browserType,String appURL) {
		initialization(browserType, appURL);
		searchPage = new SearchPage(driver);
	}

	@Test(priority=0)
	public void openSearchPage(){
		String searchPageTitle = driver.getTitle();
		String expectedPageTitle = "Google";
		Assert.assertEquals(searchPageTitle, expectedPageTitle);
	}

	@Test(priority=1)
	public void searchIndia() {
		searchPage.enterSearchText("India");
		searchPage.clickSearchButton();
	}

	@Test(priority=2)
	public void countHyperLinksDisplayed(){
		String resultsPageTitle = driver.getTitle();
		String expectedResultPageTitle = "India - Google Search";
		Assert.assertEquals(resultsPageTitle, expectedResultPageTitle);
		allPageLinks = driver.findElements(By.tagName("a"));
		System.out.println("Total Hyperlinks on page are : "+allPageLinks.size());
	}

	@Test(priority=3)
	public void verifyNoBrokenLinks(){
		for(int i=0; i<allPageLinks.size();i++){
			WebElement element = allPageLinks.get(i);
			String urlLink = element.getAttribute("href");
			try {
				URL link = new URL(urlLink);
				HttpURLConnection httpURLConnnection = (HttpURLConnection)link.openConnection();
				httpURLConnnection.connect();
				if(httpURLConnnection.getResponseCode() == 404) {
					System.out.println(urlLink+" - "+httpURLConnnection.getResponseMessage());
				}
			} catch (MalformedURLException e) {
				System.err.println("VerifyHyperLinks.verifyNoBrokenLinks : "+e.getMessage());
			} catch (IOException e) {
				System.err.println("VerifyHyperLinks.verifyNoBrokenLinks : "+e.getMessage());
			}
		}

	}

	@AfterTest
	public void afterTest() {
		driver.close();
		//driver.quit();
	}

}
