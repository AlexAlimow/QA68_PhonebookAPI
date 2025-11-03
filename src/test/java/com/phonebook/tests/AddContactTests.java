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

public class AddContactTests extends TestBase {

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
    }


    @Test
    public void addContactSuccessTest() {
        String message = given()
                .header(AUTH, token)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
        System.out.println(message);
    }

    //Contact was added! ID: 6995b47d-6a8f-4434-aa32-0e4e34761d5e

    @Test
    public void addContactWithoutNameTest() {
        // ErrorDto errorDto =
        given()
                .header(AUTH, token)
                .contentType(ContentType.JSON)
                .body(ContactDto.builder()
                        .lastName("Smith")
                        .email("js@gm.com")
                        .phone("12345678901")
                        .address("Bonn")
                        .description("coolGuy")
                        .build()
                )
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.name", containsString("must not be blank"));

        // .extract().response().as(ErrorDto.class);

        //System.out.println(errorDto.getMessage());
        //{name=must not be blank}
    }

    @Test
    public void addContactWithInvalidPhoneTest() {
        //   ErrorDto errorDto =
        given()
                .header(AUTH, token)
                .contentType(ContentType.JSON)
                .body(ContactDto.builder()
                        .name("John")
                        .lastName("Smith")
                        .email("js@gm.com")
                        .phone("1234")
                        .address("Bonn")
                        .description("coolGuy")
                        .build()
                )
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.phone", containsString("Phone number must contain only digits! And length min 10, max 15!"));
        //               .extract().response().as(ErrorDto.class);
        // System.out.println(errorDto.getMessage());
        // {phone=Phone number must contain only digits! And length min 10, max 15!}
    }
}


