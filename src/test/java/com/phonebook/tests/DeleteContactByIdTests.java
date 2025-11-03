package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.data.ObjectsData.dto;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {

    String id;
    String token;

    @BeforeMethod
    public void preRequest() {
        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().path("token");
        token = responseToken;


        String message = given()
                .header(AUTH, token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");

        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void deleteContactByIdSuccessTest() {
        // String  message =
        given()
                .header(AUTH, token)
                .when()
                .delete("contacts/" + id)
                .then()
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
                        .assertThat().body("message",containsString("not found in your contacts!"));
         //               .extract().path("message");
       // System.out.println(message);
    //Contact with id: 6995b47d-6a8f-4434-aa32-0e4e34761d5e123 not found in your contacts!
    }
}
