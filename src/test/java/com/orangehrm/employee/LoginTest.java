package com.orangehrm.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

	WebDriver driver = null;
	LoginPage loginPage = null;

	@BeforeClass
	public void open() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);

		// Navigate to this URL
		String url = "https://tejaswinimane-trials79.orangehrmlive.com/auth/login";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		Thread.sleep(3000);

		loginPage = new LoginPage(driver);

	}

	@DataProvider
	public String[][] loginData() throws IOException {

		String csvFilePath = "src/test/resources/LoginData.csv";
		BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));

		List<String[]> lines = new ArrayList<String[]>();

		String line;
		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			lines.add(fields);
		}

		String[][] data = new String[lines.size()][2];
		for (int i = 0; i < lines.size(); i++) {
			data[i] = lines.get(i);
		}

		return data;
	}

	@Test(dataProvider = "loginData")
	public void testLogin(String username, String Password) {

		loginPage.setUserName(username);
		loginPage.setPassword(Password);
		loginPage.clickLogin();

		if (loginPage.isSuccesfulLogin()) {

			AssertJUnit.assertTrue(true);
			loginPage.clickLogout();

		} else {
			String url = "https://tejaswinimane-trials79.orangehrmlive.com/auth/login";
			driver.get(url);
			AssertJUnit.assertTrue(false);

		}
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

}
