package com.wipro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

	private WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//input[@name='q' and @type='text']")
	private WebElement searchBox;
	
	@FindBy(xpath="//input[@value='Google Search' and @type='submit']")
	private WebElement searchButton;
	
	public SearchPage(WebDriver driver) {
		super();
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	public void enterSearchText(String searchText){
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(searchText);
	}
	
	public void clickSearchButton(){
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		searchButton.click();
	}
	
}
