package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTests extends TestBase {

    String token;


    @BeforeMethod
    public void preRequest() {
        String responseToken = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");
        token = responseToken;
    }

    @Test
    public void getAllContactsSuccessTest() {
        AllContactsDto contactsDto = app.getContact().getAllContacts(AUTH, token)
                .assertThat().statusCode(200)
                .extract().response().as(AllContactsDto.class);
        for (ContactDto contact : contactsDto.getContacts()) {
            System.out.println(contact.getId() + " *** " + contact.getName());
            System.out.println("*******************************************");
        }
    }



    @Test
    public void getAllContactsWithInvalidTokenTest() {
       app.getContact().getAllContacts(AUTH, "asdasdasd")
                .assertThat().statusCode(401)
                .assertThat().body("error", equalTo("Unauthorized"));

    }
}
