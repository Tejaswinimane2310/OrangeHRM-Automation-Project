package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.orangehrm.entity.Employee;

public class OrangehrmEmployeePage {

	WebDriver driver = null;

	By empList = By.xpath("//a[text() = \"Employee List \"]");
	By addEmpButton = By.xpath("//*[@id=\"addEmployeeButton\"]/i");
	By firstName = By.id("first-name-box");
	By middleName = By.id("middle-name-box");
	By lastName = By.id("last-name-box");
	By locationButton = By.xpath("//select[@id=\"location\"]/following-sibling::button");
	By locationName = By.xpath("//span[text()=\"India Office\"]");
	By saveButton = By.xpath("//button[@id=\"modal-save-button\"]");
	By empBirthday = By.id("emp_birthday");
	By maritalStatusDropdown = By.xpath("//*[@id=\"emp_marital_status_inputfileddiv\"]/div/input");
	By maritalStatusSingle = By.xpath("//span[text()=\"Single\"]");
	By genderDropdown = By.xpath("//*[@id=\"emp_gender_inputfileddiv\"]/div/input");
	By genderMale = By.xpath("//span[text()=\"Male\"]");
	By nationCodeDropdown = By.xpath("//*[@id=\"nation_code_inputfileddiv\"]/div/input");
	By nationalityIndian = By.xpath("//span[text()=\"Indian\"]");
	By licenseInputBox = By.xpath("//*[@id=\"licenseNo\"]");
	By licenseExpDate = By.xpath("//*[@id=\"emp_dri_lice_exp_date\"]");
	By nextButton = By.xpath("//*[@id=\"wizard-nav-button-section\"]/button[2]");
	By workShiftDropdown = By.xpath("//select[@name=\"work_shift_id\"]/following-sibling::button");
	By workShiftGeneral = By.xpath("//span[text()=\"General\"]");
	By regionDropdown = By.xpath("//select[@name=\"9\"]/following-sibling::button");
	By regionName = By.xpath("//span[text()=\"Region-1\"]");
	By fteDropdown = By.xpath("//select[@name=\"10\"]/following-sibling::button");
	By fteName = By.xpath("//span[text()=\"1\"]");
	By unitDropdown = By.xpath("//select[@name=\"11\"]/following-sibling::button");
	By unitName = By.xpath("//span[text()=\"Sub unit-1\"]");
	By saveEmpButton = By.xpath("//div[@id=\"wizard-nav-button-section\"]/button[3]");
	By empFullName = By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]");
	By empListVerifier = By.xpath("//li[contains(text(), \"1 - 50\")]");
	By empSearchbox = By.id("employee_name_quick_filter_employee_list_value");
	By clickNewEmp = By.xpath("//span[text()=\"Aditya Abhimanyu Mane\"]");
	By openNewEmp = By.xpath("//td[text()=\"Aditya Abhimanyu Mane \"]");
	By applyLeave = By.xpath("//*[@id=\"widget.id\"]/span/span/quick-access-widget/div/div/div[2]/span/div/span[2]/span[3]/div[1]/span/span/img");
	By leaveDate = By.id("leave.assign_fromDate");
	By applyLeaveClick = By.xpath("//*[@id=\"mount-vue-app\"]/div/div[2]/div/div[2]/form/div[13]/button/div");
	
	
	public OrangehrmEmployeePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickEmpListButton() throws InterruptedException {
		driver.findElement(empList).click();
	}


	public void addEmployee(Employee emp) throws InterruptedException {

		driver.findElement(empList).click();
		
		driver.findElement(addEmpButton).click();

		driver.findElement(firstName).sendKeys(emp.getFirstName());

	//	driver.findElement(middleName).sendKeys("Abhimanyu");

		driver.findElement(lastName).sendKeys(emp.getLastName());

		driver.findElement(locationButton).click();

		driver.findElement(locationName).click();

		driver.findElement(saveButton).click();
		
		Thread.sleep(1000);
		
		driver.findElement(saveButton).click();

		driver.findElement(empBirthday).sendKeys("Wed, 23 Oct 1996");

		driver.findElement(maritalStatusDropdown).click();

		driver.findElement(maritalStatusSingle).click();

		driver.findElement(genderDropdown).click();

		driver.findElement(By.xpath("//span[text()=\"" + emp.getGender() + "\"]")).click();

		driver.findElement(nationCodeDropdown).click();

		driver.findElement(nationalityIndian).click();

		driver.findElement(licenseInputBox).sendKeys("12345678");

		driver.findElement(licenseExpDate).sendKeys("Sat, 12 Apr 2025");

		driver.findElement(nextButton).click();

		driver.findElement(workShiftDropdown).click();

		driver.findElement(workShiftGeneral).click();

		driver.findElement(regionDropdown).click();

		driver.findElement(regionName).click();

		driver.findElement(fteDropdown).click();

		driver.findElement(fteName).click();

		driver.findElement(unitDropdown).click();

		driver.findElement(unitName).click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0,2500)");

		driver.findElement(saveEmpButton).click();

	}
	
	public boolean isEmployeeAdded(String firstName, String lastName) {
		return driver.findElement(By.xpath("//span[text()=\"" + firstName + "  " + lastName + "\"]")).isDisplayed();
	}

	public void goToHome() {
		driver.findElement(By.xpath("//*[@id=\"top-ribbon-menu\"]/div[1]/div/a/i")).click();  
	}
	
	public void searchEmployee() throws InterruptedException {

		driver.findElement(empList).click();

		driver.findElement(empListVerifier).isEnabled();

		driver.findElement(empSearchbox)
				.sendKeys("Aditya Abhimanyu Mane" + Keys.ENTER);

		driver.findElement(clickNewEmp).click();

		driver.findElement(openNewEmp).click();

	}

	public void applyLeave() throws InterruptedException {

		driver.findElement(applyLeave).click();

		driver.findElement(leaveDate).sendKeys("Mon, 05 May 2023");

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollBy(0, 500)");

		Thread.sleep(3000);

		driver.findElement(applyLeaveClick).click();

		driver.findElement(applyLeaveClick).click();

	}

}
