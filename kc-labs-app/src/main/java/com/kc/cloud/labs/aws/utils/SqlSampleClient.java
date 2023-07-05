package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.UserSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SqlSampleClient {

    private static final String QUERY = "select * from kec_users ku";

    public static List<UserSample> getAll(String url, String username, String password) {
        List<UserSample> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = null;
            stmt = conn.createStatement();
            stmt.execute(QUERY);
            while (stmt.getResultSet().next()) {
                UserSample user = new UserSample();
                user.setId(stmt.getResultSet().getInt("user_id"));
                user.setName(stmt.getResultSet().getString("user_name"));
                user.setSalary(stmt.getResultSet().getString("user_salary"));
                user.setActive(stmt.getResultSet().getBoolean("user_active"));
                user.setAge(stmt.getResultSet().getInt("user_age"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

}
