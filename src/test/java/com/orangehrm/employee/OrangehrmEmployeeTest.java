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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.entity.Employee;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		loginPage = new LoginPage(driver);
		orangehrmEmployeePage = new OrangehrmEmployeePage(driver);	
	}
	
	@DataProvider(name = "empData")
	public Employee[] employeeData() throws IOException {
		Employee emp = null;
		String csvFilePath = "src/test/resources/EmployeeData.csv";
	    BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
	    List<Employee> empList = new ArrayList<Employee>();
	   
	    String line;
	    while ((line = reader.readLine()) != null) {
	        String[] fields = line.split(",");
	        
	        emp = new Employee();
	        emp.setFirstName(fields[0]);
	        emp.setLastName(fields[1]);
	        emp.setGender(fields[2]);
	        empList.add(emp);
	    }
	    reader.close();
	    
	    Employee[] empArray = new Employee[empList.size()]; 
	    
	    for(int i = 0; i < empList.size(); i++) {
	    	empArray[i] = empList.get(i);
	    }
	    
		return empArray;
	}
	
	
	@Test
	public void doLogin() {
		
		loginPage.setUserName("Admin");
		loginPage.setPassword("L0tLjv3@TJ");
		loginPage.clickLogin();		
		Assert.assertTrue(loginPage.isSuccesfulLogin());

	}
	
	@Test(dependsOnMethods = "testLogin", dataProvider = "empData")
	public void testAddEmployee(Employee emp) {
		
		try {
			
			orangehrmEmployeePage.addEmployee(emp);
			Assert.assertTrue(orangehrmEmployeePage.isEmployeeAdded(emp.getFirstName(), emp.getLastName()));
			orangehrmEmployeePage.goToHome();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}
}
