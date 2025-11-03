package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTests extends TestBase {

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
    public void getAllContactsSuccessTest() {
        AllContactsDto contactsDto = given()
                .header(AUTH, token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AllContactsDto.class);
        for (ContactDto contact : contactsDto.getContacts()) {
            System.out.println(contact.getId() + " *** " + contact.getName());
            System.out.println("*******************************************");
        }
    }

    @Test
    public void getAllContactsWithInvalidTokenTest() {
        given()
                .header(AUTH, "asdasdasd")
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("error", equalTo("Unauthorized"));

    }
}
