package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.api.SecretManagerUtil;
import com.kc.cloud.labs.aws.models.app.UserSample;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SqlSampleClient {

    public static ResultSet getResultSet(String sql) {
        String secretId = "dev1/credentials/database";
        Map<String, String> secterMap = SecretManagerUtil.getValue(secretId);
        try (Connection conn = DriverManager.getConnection(secterMap.get("url"), secterMap.get("username"), secterMap.get("password"))) {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean executeSql(String sql){
        String secretId = "dev1/credentials/database";
        Map<String, String> secterMap = SecretManagerUtil.getValue(secretId);
        try (Connection conn = DriverManager.getConnection(secterMap.get("url"), secterMap.get("username"), secterMap.get("password"))) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static <T> List<T> findAll(String tableName, String prefix, Class<T> clazz){
        String sql = getAllSql(tableName);
        ResultSet resultSet = getResultSet(sql);
        return getAllObjectsFromResultSet(clazz, prefix, resultSet);
    }

    public static <T> T findOne(String tableName, String prefix, String id, Class<T> clazz){
        String sql = getOneSql(tableName, prefix, id);
        ResultSet resultSet = getResultSet(sql);
        List<T> objs = getAllObjectsFromResultSet(clazz, prefix, resultSet);
        return objs.get(0);
    }

    public static boolean deleteOne(String tableName, String prefix, String id){
        String sql = deleteSql(tableName, prefix, id);
        return executeSql(sql);
    }

    public static <T>boolean save(T object, String tableName, String prefix){
        String sql = insertIntoSql(object, tableName, prefix);
        return executeSql(sql);
    }

    public static <T> boolean update(T object, String tableName, String prefix){
        String sql = updateSql(object, tableName, prefix);
        return executeSql(sql);
    }

    public static String getAllSql(String tableName){
        return "SELECT * FROM " + tableName;
    }

    public static String getOneSql(String tableName, String prefix, String id){
        return "SELECT * FROM " + tableName + " WHERE " + prefix + "_id = " + id;
    }

    public static String deleteSql(String tableName, String prefix, String id){
        return "DELETE FROM " + tableName + " WHERE " + prefix + "_id = " + id;
    }

    public static <T> String insertIntoSql(T object, String tableName, String prefix){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder(" VALUES (");
        try {
            for (Field field : fields) {
                if (field.getName().equals("id")) continue;
                field.setAccessible(true);
                sql.append(prefix).append("_").append(field.getName()).append(", ");
                values.append("'").append(field.get(object)).append("', ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        values.deleteCharAt(values.length() - 2);
        values.append(")");
        return sql.append(values).toString();
    }

    public static <T> String updateSql(T object, String tableName, String prefix){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        try {
            for (Field field : fields) {
                if (field.getName().equals("id")) continue;
                field.setAccessible(true);
                sql.append(prefix).append("_").append(field.getName()).append(" = '").append(field.get(object)).append("', ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(" WHERE ").append(prefix).append("_id = ").append(((UserSample) object).getId());
        return sql.toString();
    }


    public static <T> List<T> getAllObjectsFromResultSet(Class<T> clazz, String prefix, ResultSet resultSet) {
        List<T> objects = new ArrayList<>();
        try {
            T obj;
            while (resultSet.next()) {
                obj = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(obj, resultSet.getObject(prefix +"_"+ field.getName()));
                }
                objects.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objects;
    }


}
