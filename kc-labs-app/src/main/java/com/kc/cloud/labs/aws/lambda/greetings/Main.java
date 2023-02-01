package com.kc.cloud.labs.aws.lambda.greetings;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String code = RandomStringUtils.random(10, true, true);
        System.out.println(code);
    }
}
