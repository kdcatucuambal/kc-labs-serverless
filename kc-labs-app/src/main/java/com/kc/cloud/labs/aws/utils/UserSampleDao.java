package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.User;
import com.kc.cloud.labs.aws.models.app.UserSample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserSampleDao {

    private final String tableName = "kec_users";
    private final String prefix = "user";


    public UserSampleDao(){
    }

    public List<UserSample> getAll(){
        String sql = "SELECT * FROM " + tableName;
        ResultSet resultSet = SqlSampleClient.getResultSet(sql);

        if (resultSet == null) {
            return null;
        }
        return SqlSampleClient.getAllObjectsFromResultSet(UserSample.class, prefix, resultSet);
    }

    public UserSample getOneById(String id){
        String sql = "SELECT * FROM " + tableName + " WHERE user_id = " + id;
        ResultSet resultSet = SqlSampleClient.getResultSet(sql);
        if (resultSet == null) {
            return null;
        }
        List<UserSample> users = SqlSampleClient.getAllObjectsFromResultSet(UserSample.class, prefix, resultSet);
        return users.get(0);
    }

    public boolean save(UserSample userSample){
        String sql = "INSERT INTO " + tableName + " (user_name, user_salary, user_active, user_age) VALUES ('" + userSample.getName() + "', '" + userSample.getSalary() + "', " + userSample.getActive() + ", " + userSample.getAge() + ")";
        return SqlSampleClient.executeSql(sql);
    }

    public boolean update(UserSample userSample){
        String sql = "UPDATE " + tableName + " SET user_name = '" + userSample.getName() + "', user_salary = '" + userSample.getSalary() + "', user_active = " + userSample.getActive() + ", user_age = " + userSample.getAge() + " WHERE user_id = " + userSample.getId();
        return SqlSampleClient.executeSql(sql);
    }

    public boolean delete(UserSample userSample){
        String sql = "DELETE FROM " + tableName + " WHERE user_id = " + userSample.getId();
        return SqlSampleClient.executeSql(sql);
    }

}
