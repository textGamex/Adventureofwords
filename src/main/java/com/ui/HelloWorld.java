package com.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

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
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(final KeyEvent keyEvent)
            {
                System.out.println(keyEvent.getCode());
                label.setText(keyEvent.getCode().getName());
            }
        });
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
