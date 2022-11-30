package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Setter
@Getter
@Slf4j
@Component
@Configurable
public class CustomListener implements IResultListener2 {

    CustomListener() {
        log.info("Inside Constructor");
    }

    public void onStart(ITestContext context) {
        log.info("Inside On Start " + context);

    }

    public void onTestStart(ITestResult result) {
        log.info("Inside On Test Start " + result);
    }

    public void onTestSuccess(ITestResult result) {
        log.info("Inside On Success " + result);
    }

    public void onTestFailure(ITestResult result) {
        log.info("Inside On Failure " + result);
    }

    public void onTestSkipped(ITestResult result) {
        log.info("Inside On Skipped " + result);
    }

    /**
     * This runs once test run has finished
     * @param context
     */
    public void onFinish(ITestContext context) {
        log.info("Inside On Finish " + context);
    }


    @Override
    public void onConfigurationFailure(ITestResult itr) {
        //Ignore this implementation
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {
        //Ignore this implementation
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //Ignore this implementation
    }
}