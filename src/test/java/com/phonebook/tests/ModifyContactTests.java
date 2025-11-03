package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.data.ObjectsData.dto;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ModifyContactTests extends TestBase {

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
    public void modifyContactTest(){
        ContactDto updatedContact = ContactDto.builder()
                .id(id)
                .name("Tom")
                .lastName("Johns")
                .address("Trier")
                .build();

        // String  message =
        given()
                .header(AUTH, token)
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .when()
                .put("contacts/")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was updated"));
        //.extract().path("message");
        //System.out.println(message);
    }
}

