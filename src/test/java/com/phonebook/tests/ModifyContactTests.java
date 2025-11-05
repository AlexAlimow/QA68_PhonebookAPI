package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static org.hamcrest.Matchers.equalTo;

public class ModifyContactTests extends TestBase {

    String id;
    String token;

    @BeforeMethod
    public void preRequest() {
        String responseToken = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");
        token = responseToken;

        String message = app.getContact().addContact(AUTH, token)
                .assertThat().statusCode(200)
                .extract().path("message");

        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void modifyContactSuccessTest(){
        ContactDto updatedContact = ContactDto.builder()
                .id(id)
                .name("Tom")
                .lastName("Johns")
                .address("Trier")
                .build();

        // String  message =
        app.getContact().modifyContactById(updatedContact, AUTH, token)
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was updated"));
        //.extract().path("message");
        //System.out.println(message);
    }


}

