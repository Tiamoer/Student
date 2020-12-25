package com.stu.controller;

import com.stu.dao.StuDao;
import com.stu.model.StuModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class StuTable {

    private final StuDao stuDao = new StuDao();
    private ArrayList<StuModel> students = stuDao.getAllStu();
    private StuModel chooseStu = null;  //被选择到的学生
    private Alert alert = new Alert(null);;

    @SuppressWarnings({"rawtypes","unchecked"})
    protected void showStuList() {
        //定义整个面板
        BorderPane tablePane = new BorderPane();
        tablePane.getStylesheets().add(getClass().getResource("../css/tablePane.css").toExternalForm());
        tablePane.setId("tablePane");
        tablePane.setCenter(null);

        //定义表格
        TableView<StuModel> table = new TableView<>();
        ObservableList<StuModel> date = FXCollections.observableArrayList();
        table.getStylesheets().add(getClass().getResource("../css/table.css").toExternalForm());

        //定义底部按钮的面板
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(10));

        //显示修改学生信息的面板
        GridPane updatePane = new GridPane();
        updatePane.setHgap(5);
        updatePane.setVgap(10);
        updatePane.setAlignment(Pos.CENTER);
        updatePane.setPrefSize(500,800);

        Label title = new Label("信息修改");
        updatePane.add(title,0,0);
        Label idLabel = new Label("ID:");
        updatePane.add(idLabel,0,1);
        TextField idTextField = new TextField();
        idTextField.setEditable(false);
        updatePane.add(idTextField,1,1);
        Label nameLabel = new Label("姓名:");
        updatePane.add(nameLabel,0,2);
        TextField nameTextField = new TextField();
        updatePane.add(nameTextField,1,2);
        Label ageLabel = new Label("年龄:");
        updatePane.add(ageLabel,0,3);
        TextField ageTextField = new TextField();
        updatePane.add(ageTextField,1,3);
        Label accountLabel = new Label("账号:");
        updatePane.add(accountLabel,0,4);
        TextField accountTextField = new TextField();
        accountTextField.setEditable(false);
        updatePane.add(accountTextField,1,4);
        Label passwordLabel = new Label("密码:");
        updatePane.add(passwordLabel,0,5);
        TextField passwordTextField = new TextField();
        updatePane.add(passwordTextField,1,5);
        Label statusLabel = new Label("状态码:");
        updatePane.add(statusLabel,0,6);
        TextField statusTextField = new TextField();
        updatePane.add(statusTextField,1,6);
        Button upBtn = new Button("更新");
        updatePane.add(upBtn,0,7);
        Button reBtn = new Button("清空");
        updatePane.add(reBtn,1,7);

        //更新按钮的点击事件
        upBtn.setOnMouseClicked(actionEvent -> {
            StuModel student = new StuModel(
                    Integer.parseInt(idTextField.getText()),
                    nameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    accountTextField.getText(),
                    passwordTextField.getText(),
                    Integer.parseInt(statusTextField.getText())
            );
            //更新学生信息
            if (stuDao.updateStu(student)) {
                alert.setTitle("更新成功");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(student.getStu_name()+"的信息更新成功！");
                alert.showAndWait();
                chooseStu.setStu_name(nameTextField.getText());
                chooseStu.setAge(Integer.parseInt(ageTextField.getText()));
                chooseStu.setStu_account(accountTextField.getText());
                chooseStu.setStu_password(passwordTextField.getText());
                idTextField.setText("");
                nameTextField.setText("");
                ageTextField.setText("");
                accountTextField.setText("");
                passwordTextField.setText("");
                statusTextField.setText("");
                table.getItems().clear();
                date.addAll(students);
            } else {
                alert.setTitle("错误");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("更新失败！请检查输入的数据是否符合规范！");
                alert.showAndWait();
            }
        });
        //清空按钮的点击事件
        reBtn.setOnAction(actionEvent -> {
            idTextField.setText("");
            nameTextField.setText("");
            ageTextField.setText("");
            accountTextField.setText("");
            passwordTextField.setText("");
            statusTextField.setText("");
        });

        //定义表格的列
        TableColumn tNo = new TableColumn("ID");
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
        TableColumn opCol = new TableColumn("操作");
        opCol.setMinWidth(100);
        opCol.setCellFactory((col)-> new TableCell<StuModel, String>() {
            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                this.setText(null);
                this.setGraphic(null);

                if (!b) {
                    Button choseBtn = new Button("选择");
                    Button deleteBtn = new Button("删除");
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(6);
                    gridPane.setAlignment(Pos.CENTER);
                    gridPane.add(choseBtn,0,0);
                    gridPane.add(deleteBtn,1,0);
                    this.setGraphic(gridPane);
                    deleteBtn.setOnMouseClicked(actionEvent -> {
                        StuModel student = this.getTableView().getItems().get(this.getIndex()); //返回当前选择的学生
                        date.remove(student);   //移除当前选择的数据
                        stuDao.deleteStu(student);
                        System.out.println("删除成功");
                        table.refresh();
                    });
                    choseBtn.setOnMouseClicked(actionEvent -> {
                        chooseStu = this.getTableView().getItems().get(this.getIndex());
                        idTextField.setText(String.valueOf(chooseStu.getNo()));
                        nameTextField.setText(chooseStu.getStu_name());
                        ageTextField.setText(String.valueOf(chooseStu.getAge()));
                        accountTextField.setText(chooseStu.getStu_account());
                        passwordTextField.setText(chooseStu.getStu_password());
                        statusTextField.setText(String.valueOf(chooseStu.getStu_status()));
                    });
                }
            }
        });

        //遍历所有学生并添加到表格
        date.addAll(students);

        //关闭表格按钮
        Button closeBtn = new Button("关闭表格");
        closeBtn.setOnAction(actionEvent -> {
            table.setVisible(false);
            MainWindows.borderPane.setCenter(null);
            closeBtn.setVisible(false);
            MainWindows.borderPane.setBottom(null);
        });

        //刷新表格
        Button flushBtn = new Button("刷新表格");
        flushBtn.setOnMouseClicked(actionEvent -> {
            students.clear();
            students = stuDao.getAllStu();
            table.getItems().clear();
            date.addAll(students);
        });

        gridPane.add(closeBtn,0,0);
        gridPane.add(flushBtn,1,0);

        //将表格添加到面板
        table.setItems(date);
        table.getColumns().addAll(tNo,tName,tAge,tAccount,tPassword,tStatus,opCol);
        tablePane.setLeft(table);
        tablePane.setRight(updatePane);

        MainWindows.borderPane.setCenter(tablePane);
        MainWindows.borderPane.setBottom(gridPane);
    }
}
