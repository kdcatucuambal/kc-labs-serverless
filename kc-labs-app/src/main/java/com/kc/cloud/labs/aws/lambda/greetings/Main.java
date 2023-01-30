package com.kc.cloud.labs.aws.lambda.greetings;

import com.kec.libs.utils.LibCore;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> random = List.of("x", "y","z");
        String json = LibCore.listToJson(random);
        System.out.println(json);
    }
}
