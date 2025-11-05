package com.phonebook.utils;

import com.phonebook.dto.AuthRequestDto;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[]> loginWithWrongPasswordFormCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader
                (new FileReader(("src/test/resources/wrongPassword.csv")));

        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{
                    AuthRequestDto.builder()
                            .username(split[0])
                            .password(split[1])
                            .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}
