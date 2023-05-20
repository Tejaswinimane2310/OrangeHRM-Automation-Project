package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

// Page Object Model (POM)
// This class represents the login page
public class LoginPage {

	WebDriver driver;
	String url = "https://tejaswinimane-trials79.orangehrmlive.com/auth/login";

	// Locators
	By username = By.name("txtUsername");
	By password = By.name("txtPassword");
	By loginbutton = By.xpath("//*[@id=\"frmLogin\"]/div[4]/button");
	By imgLogo = By.xpath("//img[@alt='company-branding']");
	By successfulLogin = By.xpath("//*[@id=\"top-ribbon-menu\"]/div[1]/div/a/i");
	By clickLogout = By.xpath("//*[@id=\"navbar-logout\"]/a");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void goToLoginPage() {
		driver.get(url);
	}
	
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

		boolean flag = false;

		try {
			flag = driver.findElement(imgLogo).isDisplayed();
			;
		} catch (NoSuchElementException e) {
			return false;
		}

		return flag;
	}

	public boolean isSuccesfulLogin() {

		return !"https://tejaswinimane-trials79.orangehrmlive.com/securityAuthentication/retryLogin"
				.equals(driver.getCurrentUrl());
	}

	public void clickLogout() {

		driver.findElement(clickLogout).click();
	}
}
