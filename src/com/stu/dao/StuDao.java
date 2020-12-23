package com.stu.dao;

import com.stu.model.StuModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 学生表数据层，里面的所有方法都是用来操作数据库的
 */
public class StuDao {

    Connection connection = ConMysql.getCon();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    StuModel stu = null;

    //返回所有学生
    public ArrayList<StuModel> getAllStu() {
        ArrayList<StuModel> students = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from stu");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stu = new StuModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
                students.add(stu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return students;
    }

    //根据传入的账号和密码来查询学生
    public StuModel checkLogin(String stuAccount,String stuPassword) {
        try {
            preparedStatement = connection.prepareStatement("select * from stu where stu_Account = ? and stu_Password = ?");
            preparedStatement.setString(1,stuAccount);
            preparedStatement.setString(2,stuPassword);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new StuModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    //根据传入的学生帐号来获得学生
    public StuModel getStu(String stuAccount) {
        try {
            preparedStatement = connection.prepareStatement("select * from stu where stu_Account = ?");
            preparedStatement.setString(1,stuAccount);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new StuModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    //根据传入的学生序号来获得学生
    public StuModel getStu(int stuNo) {
        try {
            preparedStatement = connection.prepareStatement("select * from stu where no = ?");
            preparedStatement.setInt(1,stuNo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new StuModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
