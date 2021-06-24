package com.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author 留恋千年
 *
 */
public class HelloWorld extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Label label = new Label();
        label.setText("Hello World");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 350, 350);
        scene.setOnKeyPressed(keyEvent ->
        {
            System.out.println(keyEvent.getCode());
            label.setText(keyEvent.getCode().getName());
        });
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
