package com.anm.spring.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class ConvertJavaObjtoJson {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> void writeToJson(T object, String fileName) {
        try {
            objectMapper.writeValue(new File("src/main/resources/static/" + fileName), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
