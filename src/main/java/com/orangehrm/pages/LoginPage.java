package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object Model (POM)
// This class represents the login page
public class LoginPage {
	
	WebDriver driver;
	
	// Locators
	By username = By.name("txtUsername");
	By password = By.name("txtPassword");
	By loginbutton = By.xpath("//*[@id=\"frmLogin\"]/div[4]/button");
	By imgLogo = By.xpath("//img[@alt='company-branding']");
	By successfulLogin = By.xpath("//*[@id=\"top-ribbon-menu\"]/div[1]/div/a/i");
		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
		
	// Actions
	public void setUserName(String name) {
		driver.findElement(username).sendKeys(name);
	}
	
	public void setPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}
	
	public void clickLogin() {
		driver.findElement(loginbutton).click();
	}
	
	public boolean isLogoPresent() {
		return driver.findElement(imgLogo).isDisplayed();
	}
	
	public boolean isSuccesfulLogin() {
		return driver.findElement(successfulLogin).isDisplayed();
	}
	

}
