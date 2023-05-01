package com.orangehrm.employee;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangehrmEmployee {

	WebDriver driver = null;

	@BeforeClass
	public void open() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.manage().window().maximize();

	}

	@BeforeMethod()
	public void login() throws InterruptedException {

		driver.get("https://tejaswinimane-trials79.orangehrmlive.com/auth/login");

		driver.findElement(By.xpath("//input[@id=\"txtUsername\"]")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("L0tLjv3@TJ");

		driver.findElement(By.xpath("//*[@id=\"frmLogin\"]/div[4]/button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"topbar\"]/ul[1]/li/div")).getText(),
				"Employee Management");

	}

	@Test(priority = 1, enabled = true)
	public void employeeList() throws InterruptedException {

		driver.findElement(By.xpath("//a[text() = \"Employee List \"]")).click();

		Thread.sleep(3000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0,2500)");

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(), \"1 - 50\")]")).isEnabled());

	}

	@Test(priority = 2, enabled = true)
	public void addEmployee() throws InterruptedException {

		driver.findElement(By.xpath("//a[text() = \"Employee List \"]")).click();

		driver.findElement(By.xpath("//*[@id=\"addEmployeeButton\"]/i")).click();

		driver.findElement(By.id("first-name-box")).sendKeys("Aditya");

		driver.findElement(By.id("middle-name-box")).sendKeys("Abhimanyu");

		driver.findElement(By.id("last-name-box")).sendKeys("Mane");

		driver.findElement(By.xpath("//select[@id=\"location\"]/following-sibling::button")).click();

		driver.findElement(By.xpath("//span[text()=\"India Office\"]")).click();

		driver.findElement(By.xpath("//button[@id=\"modal-save-button\"]")).click();

		driver.findElement(By.xpath("//button[@id=\"modal-save-button\"]")).click();

		driver.findElement(By.id("emp_birthday")).sendKeys("Wed, 23 Oct 1996");

		driver.findElement(By.xpath("//*[@id=\"emp_marital_status_inputfileddiv\"]/div/input")).click();

		driver.findElement(By.xpath("//span[text()=\"Single\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"emp_gender_inputfileddiv\"]/div/input")).click();

		driver.findElement(By.xpath("//span[text()=\"Male\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"nation_code_inputfileddiv\"]/div/input")).click();

		driver.findElement(By.xpath("//span[text()=\"Indian\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"licenseNo\"]")).sendKeys("12345678");

		driver.findElement(By.xpath("//*[@id=\"emp_dri_lice_exp_date\"]")).sendKeys("Sat, 12 Apr 2025");

		driver.findElement(By.xpath("//*[@id=\"wizard-nav-button-section\"]/button[2]")).click();

		driver.findElement(By.xpath("//select[@name=\"work_shift_id\"]/following-sibling::button")).click();

		driver.findElement(By.xpath("//span[text()=\"General\"]")).click();

		driver.findElement(By.xpath("//select[@name=\"9\"]/following-sibling::button")).click();

		driver.findElement(By.xpath("//span[text()=\"Region-1\"]")).click();

		driver.findElement(By.xpath("//select[@name=\"10\"]/following-sibling::button")).click();

		driver.findElement(By.xpath("//span[text()=\"1\"]")).click();

		driver.findElement(By.xpath("//select[@name=\"11\"]/following-sibling::button")).click();

		driver.findElement(By.xpath("//span[text()=\"Sub unit-1\"]")).click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0,2500)");

		driver.findElement(By.xpath("//div[@id=\"wizard-nav-button-section\"]/button[3]")).click();

		String fullName = driver.findElement(By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]")).getText();

		Assert.assertEquals(fullName, "Aditya Abhimanyu Mane");

	}

	@Test(priority = 3, enabled = true)
	public void searchEmployee() throws InterruptedException {

		driver.findElement(By.xpath("//a[text() = \"Employee List \"]")).click();

		driver.findElement(By.xpath("//li[contains(text(), \"1 - 50\")]")).isEnabled();

		driver.findElement(By.id("employee_name_quick_filter_employee_list_value"))
				.sendKeys("Aditya Abhimanyu Mane" + Keys.ENTER);

		driver.findElement(By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]")).click();

		driver.findElement(By.xpath("//td[text()=\"Aditya Abhimanyu Mane \"]")).click();

		String fullName = driver.findElement(By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]")).getText();

		Assert.assertEquals(fullName, "Aditya Abhimanyu Mane");

	}

	
	@Test(priority = 4, enabled = true)
	public void applyLeave() {
		
		
		
	}
	
	
	@AfterClass
	public void logOut() {

		driver.quit();
	}

}
