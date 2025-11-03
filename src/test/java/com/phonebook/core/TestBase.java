package com.phonebook.core;

import org.testng.annotations.BeforeMethod;

public class TestBase {
    //public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZ29ybHVtMDA3dXNlckBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc2Mjc2MTA1NSwiaWF0IjoxNzYyMTYxMDU1fQ.EcycxfIRPRjjryJ6LyAxHBBRb2BQwaFeTgxrx9PHb80";
    
    
    public static final String AUTH = "Authorization";
    protected final AppManager app = new AppManager();

    @BeforeMethod
    public void init(){
        app.start();
    }

}
