package com.stu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMysql {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String name = "root";
    private final static String pass = "745700yxy";
    private final static String url = "jdbc:mysql://localhost:3306/db_student?useSSL=false";

    public static Connection getCon() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,name,pass);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("数据库连接失败！");
            throwables.printStackTrace();
        }
        return null;
    }
}
