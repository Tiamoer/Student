package com.stu.controller;

import com.stu.service.StuService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class LoginWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("学生登陆");
        stage.setWidth(500);
        stage.setHeight(300);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Label stuAccountText = new Label("账号：");
        TextField accountTextField = new TextField();
        Label stuPassText = new Label("密码：");
        TextField passTextField = new TextField();
        Button sbtn = new Button("登录");

        grid.add(stuAccountText,0,0);
        grid.add(accountTextField,1,0);
        grid.add(stuPassText,0,1);
        grid.add(passTextField,1,1);
        grid.add(sbtn,1,2);
        //登录按钮点击事件
        sbtn.setOnAction(actionEvent -> {
            //登陆验证
            StuService stuService = new StuService();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("信息提示");
            alert.setHeaderText(null);
            int loginIndex = stuService.checkLogin(accountTextField.getText(),passTextField.getText());
            if (loginIndex==1) {
                alert.setContentText("登陆成功！");
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
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("登陆失败！");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid,300,275);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
