package com.phonebook.endpoints;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static com.phonebook.data.ObjectsData.dto;
import static io.restassured.RestAssured.given;

public class ContactHelper {

    public ValidatableResponse getAllContacts(String auth, String userToken) {
        return given()
                .header(auth, userToken)
                .when()
                .get("contacts")
                .then();
    }

    public ValidatableResponse addContact(String auth, String userToken) {
        return given()
                .header(auth, userToken)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("contacts")
                .then();
    }

    public ValidatableResponse deleteContactById(String auth, String userToken, String contactId) {
        return given()
                .header(auth, userToken)
                .when()
                .delete("contacts/" + contactId)
                .then();
    }

    public ValidatableResponse modifyContactById(ContactDto updatedContact, String auth, String userToken) {
        return given()
                .header(auth, userToken)
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .when()
                .put("contacts/")
                .then();
    }
}
