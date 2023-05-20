package com.orangehrm.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class OrangeHrmListener implements ITestListener {
	ExtentSparkReporter sr;
	ExtentReports reports;
	ExtentTest test;

	public void onStart(ITestContext context) {

		sr = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/OrangeHrmTestReport.html");

		sr.config().setDocumentTitle("My Automation Report");
		sr.config().setReportName("Orange HRM Functional Testing");
		sr.config().setTheme(Theme.STANDARD);

		reports = new ExtentReports();
		reports.attachReporter(sr);
		reports.setSystemInfo("Author", "Tejaswini");
		reports.setSystemInfo("OS", "Mac OS");
		reports.setSystemInfo("Browser", "Chrome");
	}

	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(result.getName());
		test.log(Status.PASS, "Test case passed!! : " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getName());
		test.log(Status.FAIL, "Test case failed!! : " + result.getName());
		test.log(Status.FAIL, "Test case failed reason: " + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped!! : " + result.getName());
	}

	public void onFinish(ITestContext context) {
		reports.flush(); 
	}

}
