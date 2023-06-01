package com.kc.cloud.labs.aws.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class KcUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String convertInputStreamToString(InputStream inputStream)  {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static String serializeObject(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T deserializeObject(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static <T> T deserializeObject(String json, TypeReference<T> typeReference) throws IOException {
        return mapper.readValue(json, typeReference);
    }

}
