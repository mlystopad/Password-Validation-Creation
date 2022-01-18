package com.example.passwordvalidator;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopOut {

        public static void popOut (String title, String message) {
            Stage window = new Stage();

            //Stage settings
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(200);

            //Contents
            Text output = new Text(message);
            Button button1 = new Button("Ok!");

            //Actions
            button1.setOnAction(event -> window.close());

            //Layout
            HBox layout = new HBox(20);
            layout.setPadding(new Insets( 20, 20, 20, 20));
            layout.getChildren().addAll(output, button1);

            Scene scene1 = new Scene(layout);
            window.setScene(scene1);
            window.showAndWait();
        }
}
