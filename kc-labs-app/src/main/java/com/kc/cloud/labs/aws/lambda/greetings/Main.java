package com.kc.cloud.labs.aws.lambda.greetings;

import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("/home/kdcatucuambal/Documents/GitHub/kc-labs-serverless/kc-labs-app/src/main/resources/event.json");
            //Read input stream into a string
            String requestBody = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(requestBody);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
