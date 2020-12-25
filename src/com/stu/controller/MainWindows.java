package com.stu.controller;

import com.stu.dao.StuDao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 主窗口
 */
public class MainWindows extends Application {

    static Stage stage;
    static BorderPane borderPane;
    StuDao stuDao = new StuDao();

    //学生管理菜单菜单
    private Menu[] stuMenu() {
        Menu[] menus = new Menu[5];
        Menu stuManager = new Menu("学生管理");
        Menu stuManager1 = new Menu("待定菜单");
        Menu stuManager2= new Menu("待定菜单");
        Menu stuManager3 = new Menu("待定菜单");
        Menu stuManager4 = new Menu("待定菜单");
        MenuItem stuList = new MenuItem("学生列表");
        MenuItem stuAdd = new MenuItem("添加学生");
//        MenuItem stuDelete = new MenuItem("删除学生");
//        MenuItem stuUpdata = new MenuItem("修改信息");
        MenuItem exit = new MenuItem("退出系统");
        stuManager.getItems().addAll(stuList, stuAdd, new SeparatorMenuItem(), exit);
        //学生列表
        stuList.setOnAction(actionEvent -> new StuTable().showStuList());
        //添加学生
        stuAdd.setOnAction(actionEvent -> new AddStudent().add());
        //修改学生信息
//        stuUpdata.setOnAction(actionEvent -> showStuUpdata());
        //退出系统
        exit.setOnAction(actionEvent -> {
            stage.close();
            try {
                new LoginWindow().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menus[0] = stuManager;
        menus[1] = stuManager1;
        menus[2] = stuManager2;
        menus[3] = stuManager3;
        menus[4] = stuManager4;
        return menus;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("学生信息管理系统");
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
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
