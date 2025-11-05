package com.phonebook.tests;

import com.phonebook.core.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {

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
    public void deleteContactByIdSuccessTest() {
        // String  message =
        app.getContact().deleteContactById(AUTH, token, id)
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
        //.extract().path("message")

        //System.out.println(message);

    }


    @Test
    public void deleteContactWithWrongIdTest() {
        //String message =
        given()
                .header(AUTH, token)
                .when()
                .delete("contacts/6995b47d-6a8f-4434-aa32-0e4e34761d5e123")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts!"));
        //               .extract().path("message");
        // System.out.println(message);
        //Contact with id: 6995b47d-6a8f-4434-aa32-0e4e34761d5e123 not found in your contacts!
    }

    @Test
    void deleteAllContactsTest() {
        // String message =
        given()
                .header(AUTH, token)
                .when()
                .delete("contacts/clear")
                .then()
                .assertThat().statusCode(200)
                //      .extract().path("message");
                .assertThat().body("message", containsString("All contacts was deleted!"));
        //System.out.println(message);

    }
}
