package com.bsuir.graphic.editor;

import javafx.application.Application;
import javafx.stage.Stage;
import com.bsuir.graphic.editor.ui.MainWindow;

public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) {
        new MainWindow(primaryStage);
    }
}
