package com.phonebook.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    //public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZ29ybHVtMDA3dXNlckBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc2Mjc2MTA1NSwiaWF0IjoxNzYyMTYxMDU1fQ.EcycxfIRPRjjryJ6LyAxHBBRb2BQwaFeTgxrx9PHb80";


    public static final String AUTH = "Authorization";
    protected final AppManager app = new AppManager();

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void init(Method method, Object[] p) {
        logger.info("Start test: {} with data {}", method.getName(), Arrays.asList(p));
        app.start();
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: {}", result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: {}", result.getMethod().getMethodName());
        }
        logger.info("Stop test");
        logger.info("==================================================");
    }


}
