package com.phonebook.core;

import com.phonebook.endpoints.ContactHelper;
import com.phonebook.endpoints.UserHelper;
import io.restassured.RestAssured;

public class AppManager {

    UserHelper user;
    ContactHelper contact;

    public void start() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

        user = new UserHelper();
        contact = new ContactHelper();
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

}
