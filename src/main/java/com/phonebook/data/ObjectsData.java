package com.phonebook.data;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;

public class ObjectsData {

    public static AuthRequestDto auth = AuthRequestDto.builder()
            .username("gorlum007user@gmail.com")
            .password("TestTest007!")
            .build();

    public static ContactDto dto = ContactDto.builder()
            .name("John")
            .lastName("Smith")
            .email("js@gm.com")
            .phone("12345678901")
            .address("Bonn")
            .description("coolGuy")
            .build();
}
