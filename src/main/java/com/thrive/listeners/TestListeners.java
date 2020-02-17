package com.thrive.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.logger.LoggerConfig;
import com.thrive.mailing.StatusMailing;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.screenshot.Screenshot;
import com.thrive.utils.Property;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
//		extentReports.extTest.pass("The Test Case '" + result.getName() + "' execution is Completed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            ExtentReports.getChildTest().fail("The Test Case '" + result.getName() + "' has Failed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.shot()).build());

            // Logger Error
            LoggerConfig.getLogger().error(result.getThrowable());
            if (Property.getProperty("extent").equalsIgnoreCase("on")) {
                Screenshot.shot(result.getName());

                // Logger Info
                LoggerConfig.getLogger().info("Screenshot is saved locally");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        // Setting the logger
        LoggerConfig.setLogger(getClass().getName());

        ExtentReports.getChildTest().skip("The Test Case '" + result.getName() + "' has Skipped");

        // Logger warn
        LoggerConfig.getLogger().warn("The Test Case '" + result.getName() + "' has Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        // Extent reports will be flushed only if extent reports are On
        // So Reports will be generated only if extent is flushed
        if (Property.getProperty("extent").equalsIgnoreCase("On")) {
            ExtentReports.getExtent().flush();
            LoggerConfig.getLogger().info("Extent Report is generated");

            // Send mails only if set to Yes
            if (System.getProperty("mail").equalsIgnoreCase("Yes")) {
                StatusMailing.report_mail();
            }
        }

    }

}
