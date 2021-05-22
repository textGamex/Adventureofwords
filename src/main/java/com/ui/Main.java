package com.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

import static com.java.message.PrivateData.PASSWORD1;
import static com.java.message.PrivateData.PASSWORD2;

public class Main extends Application
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Main.class);
    @Override
    public void start(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 80);
        stage.setScene(scene);
        stage.setTitle("请输入管理员密码");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        scene.setRoot(grid);

        Button submit = new Button("确定");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(15);//设置文本域长度
        grid.getChildren().add(passwordField);

        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        submit.setOnAction((ActionEvent e) ->
        {
            if ((passwordField.getText() != null && !passwordField.getText().isEmpty()))
            {
                var password = passwordField.getText();
                if (password.equals(PASSWORD1) || password.equals(PASSWORD2))
                {
                    label.setText("登录成功!");
                    LOGGER.debug("登录成功");
                }
                else
                {
                    LOGGER.debug("密码错误");
                    label.setText("密码错误! 3秒后游戏退出!");
                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e1)
                    {
                        e1.printStackTrace();
                    }
                    System.exit(2);
                }
            }
            else
            {
                label.setText("请输入密码!");
            }
        });

        stage.show();
    }

}