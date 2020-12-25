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

        //主面板
        BorderPane addPane = new BorderPane();
        addPane.getStylesheets().addAll(this.getClass().getResource("../css/addPane.css").toExternalForm());

        //标题面板
        GridPane titlePane = new GridPane();
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setId("gridPane");
        //底部按钮面板
        GridPane btnPane = new GridPane();
        btnPane.setAlignment(Pos.CENTER);
        btnPane.setId("gridPane");
        btnPane.setHgap(20);
        btnPane.setPadding(new Insets(10));
        //添加表单面板
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setVgap(10);
        formPane.setHgap(5);

        //titlePane组件
        Label title = new Label("学生信息添加");
        title.setId("title");
        titlePane.add(title,0,0);

        //formPane组件
        Label nameLabel = new Label("姓名:");
        formPane.add(nameLabel,0,2);
        TextField nameTextField = new TextField();
        formPane.add(nameTextField,1,2);
        Label ageLabel = new Label("年龄");
        formPane.add(ageLabel,0,3);
        TextField ageTextField = new TextField();
        formPane.add(ageTextField,1,3);
        Label accountLabel = new Label("账号");
        formPane.add(accountLabel,0,4);
        TextField accountTextField = new TextField();
        formPane.add(accountTextField,1,4);
        Label passwordLabel = new Label("密码");
        formPane.add(passwordLabel,0,5);
        TextField passwordTextField = new TextField();
        formPane.add(passwordTextField,1,5);
        Label statusLabel = new Label("状态码");
        formPane.add(statusLabel,0,6);
        TextField statusTextField = new TextField();
        statusTextField.setEditable(false);
        statusTextField.setText("0");
        formPane.add(statusTextField,1,6);

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
        addPane.setTop(titlePane);

        MainWindows.borderPane.setCenter(addPane);
        MainWindows.borderPane.setBottom(btnPane);
    }
}
