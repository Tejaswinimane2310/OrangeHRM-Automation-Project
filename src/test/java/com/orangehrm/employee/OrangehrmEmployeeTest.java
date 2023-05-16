package com.orangehrm.employee;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.OrangehrmEmployeePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangehrmEmployeeTest {
	
	WebDriver driver = null;
	LoginPage loginPage = null;
	OrangehrmEmployeePage orangehrmEmployeePage = null;
	
	
	@BeforeClass
	public void open() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);


		// Navigate to this URL
		String url = "https://tejaswinimane-trials79.orangehrmlive.com/auth/login";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		loginPage = new LoginPage(driver);
		orangehrmEmployeePage = new OrangehrmEmployeePage(driver);	
	}
	
	@Test
	public void testLogin() {
		loginPage.setUserName("Admin");
		loginPage.setPassword("L0tLjv3@TJ");
		loginPage.clickLogin();		
		Assert.assertTrue(loginPage.isSuccesfulLogin());
	}
	
	@Test(dependsOnMethods = "testLogin")
	public void testAddEmployee() {
		
		try {
			orangehrmEmployeePage.addEmployee();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String fullName = driver.findElement(By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]")).getText();

		Assert.assertEquals(fullName, "Aditya Abhimanyu Mane");
		
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}
}
