package com.wipro.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 2;
    
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCount+1));
            retryCount++;
            return true;
        }
        return false;
    }

}
