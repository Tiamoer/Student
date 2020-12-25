package com.stu.controller;

import com.stu.dao.StuDao;
import com.stu.model.StuModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

//  添加学生
public class AddStudent  {

    private StuDao stuDao = new StuDao();
    private Alert alert = new Alert(null);

    public void add() {

        //主面板 1200*800
        BorderPane addPane = new BorderPane();
        addPane.getStylesheets().addAll(this.getClass().getResource("../css/addPane.css").toExternalForm());
        addPane.setId("addPane");

        //底部按钮面板
        GridPane btnPane = new GridPane();
        btnPane.getStylesheets().addAll(this.getClass().getResource("../css/addPane.css").toExternalForm());
        btnPane.setAlignment(Pos.CENTER);
        btnPane.setId("gridPane");
        btnPane.setHgap(20);
        btnPane.setPadding(new Insets(0,0,60,0));
        //添加表单面板
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(-50,0,0,0));
        formPane.setVgap(10);
        formPane.setHgap(5);

        //formPane组件
        Label nameLabel = new Label("姓名:");
        formPane.add(nameLabel,0,1);
        TextField nameTextField = new TextField();
        formPane.add(nameTextField,1,1);
        Label ageLabel = new Label("年龄:");
        formPane.add(ageLabel,0,2);
        TextField ageTextField = new TextField();
        formPane.add(ageTextField,1,2);
        Label accountLabel = new Label("账号:");
        formPane.add(accountLabel,0,3);
        TextField accountTextField = new TextField();
        formPane.add(accountTextField,1,3);
        Label passwordLabel = new Label("密码:");
        formPane.add(passwordLabel,0,4);
        TextField passwordTextField = new TextField();
        formPane.add(passwordTextField,1,4);
        Label statusLabel = new Label("状态码:");
        formPane.add(statusLabel,0,5);
        TextField statusTextField = new TextField();
        statusTextField.setEditable(false);
        statusTextField.setText("0");
        formPane.add(statusTextField,1,5);

        //btnPane组件
        Button addBtn = new Button("添加");
        btnPane.add(addBtn,0,1);
        Button reBtn = new Button("清空");
        btnPane.add(reBtn,1,1);

        addBtn.setOnMouseClicked(actionEvent -> {
            StuModel student = new StuModel(
                    nameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    accountTextField.getText(),
                    passwordTextField.getText(),
                    Integer.parseInt(statusTextField.getText())
            );
            if (stuDao.addStu(student)) {
                alert.setTitle("更新成功");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(student.getStu_name()+"的信息添加成功！");
                alert.showAndWait();
                nameTextField.setText("");
                ageTextField.setText("");
                accountTextField.setText("");
                passwordTextField.setText("");
                statusTextField.setText("");
            } else {
                alert.setTitle("错误");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("更新失败！请检查输入的数据是否符合规范！");
                alert.showAndWait();
            }
        });

        reBtn.setOnMouseClicked(actionEvent -> {
            nameTextField.setText("");
            ageTextField.setText("");
            accountTextField.setText("");
            passwordTextField.setText("");
            statusTextField.setText("");
        });

        addPane.setCenter(formPane);

        MainWindows.borderPane.setCenter(addPane);
        MainWindows.borderPane.setBottom(btnPane);
    }
}
