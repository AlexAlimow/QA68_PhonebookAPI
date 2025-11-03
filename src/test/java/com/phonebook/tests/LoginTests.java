package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase {


    @Test
    public void loginSuccessTest() {
        AuthResponseDto dto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        //System.out.println(dto.getToken());
    }

    @Test
    public void loginSuccessTest2() {
        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().path("token");
        //System.out.println(responseToken);
    }

    // assert with TestNG
    @Test
    public void liginWithWrongPasswordTest() {
        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("gorlum007user@gmail.com")
                        .password("aawda!fgg88"))
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);
        Assert.assertEquals(errorDto.getError(), "Unauthorized");
        //System.out.println(errorDto.getError() + " *** " + errorDto.getMessage());
        //Unauthorized *** Login or Password incorrect
    }

    //assert with RestAssured
    @Test
    public void  loginWithWrongEmailTest(){
        given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDto.builder()
                        .username("gorlum007us@gmail.com")
                        .password("TestTest007!"))
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Login or Password incorrect"));
    }
}
