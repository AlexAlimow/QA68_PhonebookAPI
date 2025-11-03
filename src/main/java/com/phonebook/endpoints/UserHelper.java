package com.phonebook.endpoints;

import com.phonebook.dto.AuthRequestDto;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UserHelper {

    public String getToken(AuthRequestDto auth) {
        return given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().path("token");
    }
}
