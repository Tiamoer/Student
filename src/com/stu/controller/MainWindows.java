package com.stu.controller;

import com.stu.dao.StuDao;
import com.stu.model.StuModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.PropertyPermission;

/**
 * 主窗口
 */
public class MainWindows extends Application {

    Stage stage;
    StuDao stuDao = new StuDao();;
    BorderPane borderPane;

    //学生管理菜单菜单
    private Menu stuMenu() {
        Menu stuManager = new Menu("学生管理");
        MenuItem stuList = new MenuItem("学生列表");
        MenuItem stuAdd = new MenuItem("添加学生");
        MenuItem stuDelete = new MenuItem("删除学生");
        MenuItem stuUpdata = new MenuItem("修改信息");
        MenuItem exit = new MenuItem("退出系统");
        stuManager.getItems().addAll(stuList, stuAdd,stuDelete,stuUpdata, new SeparatorMenuItem(),exit);
        //学生列表
        stuList.setOnAction(actionEvent -> {
            showStuList();
        });
        //添加学生
        stuAdd.setOnAction(actionEvent -> {
            showStuAdd();
        });
        //删除学生
        stuDelete.setOnAction(actionEvent -> {
            showStuDelete();
        });
        //修改学生信息
        stuUpdata.setOnAction(actionEvent -> {
            showStuUpdata();
        });
        //退出系统
        exit.setOnAction(actionEvent -> {
            stage.close();
            try {
                new LoginWindow().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return stuManager;
    }

    private void showStuList() {
        //显示学生信息的面板
        ArrayList<StuModel> students = stuDao.getAllStu();

        //定义表格
        TableView<StuModel> table = new TableView<>();
        ObservableList<StuModel> date = FXCollections.observableArrayList();
        TableColumn tNo = new TableColumn("编号");
        tNo.setMinWidth(100);
        tNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        TableColumn tName = new TableColumn("姓名");
        tName.setMinWidth(100);
        tName.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        TableColumn tAge = new TableColumn("年龄");
        tAge.setMinWidth(100);
        tAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn tAccount = new TableColumn("账户");
        tAccount.setMinWidth(100);
        tAccount.setCellValueFactory(new PropertyValueFactory<>("stu_account"));
        TableColumn tPassword = new TableColumn("密码");
        tPassword.setMinWidth(100);
        tPassword.setCellValueFactory(new PropertyValueFactory<>("stu_password"));
        TableColumn tStatus = new TableColumn("状态");
        tStatus.setMinWidth(100);
        tStatus.setCellValueFactory(new PropertyValueFactory<>("stu_status"));

        //遍历所有学生并添加到表格
        for (StuModel student : students) {
            date.add(student);
        }

        //关闭表格按钮
        Button btn = new Button("关闭表格");
        btn.setOnAction(actionEvent -> {
            table.setVisible(false);
            borderPane.setCenter(null);
            btn.setVisible(false);
            borderPane.setBottom(null);
        });

        //将表格添加到面板
        table.setItems(date);
        table.getColumns().addAll(tNo,tName,tAge,tAccount,tPassword,tStatus);
        borderPane.setCenter(table);
        borderPane.setBottom(btn);
    }

    private void showStuAdd() {
        //显示添加学生的面板
    }

    private void showStuDelete() {
        //显示删除学生的面板
    }

    private void showStuUpdata() {
        //显示更新学生信息的面板
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("学生信息管理系统");
        stage.setWidth(1200);
        stage.setHeight(800);
        //菜单模块
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(stuMenu());
        //菜单面板
        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        //主窗口
        Scene scene = new Scene(borderPane, 300, 275);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
