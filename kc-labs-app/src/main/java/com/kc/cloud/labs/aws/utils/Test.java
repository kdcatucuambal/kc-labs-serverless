package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.User;
import com.kc.cloud.labs.aws.models.app.UserSample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        UserSampleDao2 userSampleDao2 = new UserSampleDao2();
        UserSample userSample = new UserSample();
        userSample.setName("Kevin Catucuamba");
        userSample.setSalary("1000.45");
        userSample.setActive(true);
        userSample.setAge(30);
        boolean isSaved = userSampleDao2.save(userSample);
        System.out.println("isSaved: " + isSaved);
    }


    public static <T> T getValues(Class<T> clazz){
        T obj;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static <T> T createObjectFromResultSet(Class<T> clazz, ResultSet resultSet) {
        T obj;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, resultSet.getObject(field.getName()));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}
