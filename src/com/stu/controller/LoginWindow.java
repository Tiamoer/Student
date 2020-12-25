package com.stu.controller;

import com.stu.service.StuService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

public class LoginWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("学生信息管理系统");
        stage.getIcons().add(new Image("com/stu/img/logo.png"));
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setResizable(false);

        //窗口BorderPane布局
        BorderPane borderPane = new BorderPane();

        //标题栏布局
        GridPane gridTitle = new GridPane();
        gridTitle.setAlignment(Pos.CENTER);
        gridTitle.setPadding(new Insets(35,0,0,0));
        Label title = new Label("学生信息管理系统");
        title.setId("title");
        gridTitle.add(title,0,0);


        //登陆表单网格布局
        GridPane gridForm = new GridPane();
        gridForm.setAlignment(Pos.CENTER);
        gridForm.setHgap(10);
        gridForm.setVgap(10);
        gridForm.setPadding(new Insets(25,25,25,25));

        Label stuAccountText = new Label("账号：");
        stuAccountText.setId("label");
        TextField accountTextField = new TextField();
        Label stuPassText = new Label("密码：");
        PasswordField passTextField = new PasswordField();

        gridForm.add(stuAccountText,0,0);
        gridForm.add(accountTextField,1,0);
        gridForm.add(stuPassText,0,1);
        gridForm.add(passTextField,1,1);

        //按钮面板
        GridPane gridBtn = new GridPane();
        gridBtn.setPadding(new Insets(0,0,40,0));
        gridBtn.setAlignment(Pos.CENTER);
        gridBtn.setHgap(30);
        Button sbtn = new Button("登录");
        Button rbtn = new Button("重置");
        gridBtn.add(sbtn,0,0);
        gridBtn.add(rbtn,1,0);

        //登录按钮点击事件
        sbtn.setOnAction(actionEvent -> {
            //登陆验证
            StuService stuService = new StuService();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("信息提示");
            alert.setHeaderText(null);
            int loginIndex = stuService.checkLogin(accountTextField.getText(),passTextField.getText());
            if (loginIndex==1) {
                alert.setContentText("登录成功！");
                alert.showAndWait();
                //关闭当前界面.打开主页面
                try {
                    new MainWindows().start(new Stage());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(loginIndex==2) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("账户被冻结！请联系管理员解除冻结！");
                alert.showAndWait();
            } else {
                alert.setTitle("登录失败");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("请检查您的账号和密码是否正确！");
                alert.showAndWait();
            }
        });
        //重置按钮点击事件
        rbtn.setOnMouseClicked(actionEvent -> {
            accountTextField.setText("");
            passTextField.setText("");
        });

        //布局嵌套
        borderPane.setTop(gridTitle);
        borderPane.setCenter(gridForm);
        borderPane.setBottom(gridBtn);

        Scene scene = new Scene(borderPane,300,275);
        scene.getStylesheets().addAll(this.getClass().getResource("../css/login.css").toExternalForm());
        borderPane.setId("LoginPane");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
