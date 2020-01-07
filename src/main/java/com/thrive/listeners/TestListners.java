package com.thrive.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.modules.screenshot;
import com.thrive.reportSetup.extentReports;
import com.thrive.utils.Property;

public class TestListners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
//		extentReports.extTest.pass("The Test Case '" + result.getName() + "' execution is Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		try {
			extentReports.getChildTest().fail("The Test Case '" + result.getName() + "' has Failed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot.shot()).build());
			if (Property.getProperty("extent").equalsIgnoreCase("on")) {
				screenshot.shot(result.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		extentReports.getChildTest().skip("The Test Case '" + result.getName() + "' has Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
//		extentReports.extTest.pass("The Test Case '" + context.getName() + "' execution has Stopped");
	}

}
