package com.example.passwordvalidator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class App extends Application {
    @Override
    public void start(Stage mainStage) throws Exception {
        //Contents
        TextField passwordField = new TextField();
        passwordField.setMaxWidth(200);

        CheckBox check1 = new CheckBox("Lower Case");
        check1.setSelected(true);
        CheckBox check2 = new CheckBox("Upper Case");
        CheckBox check3 = new CheckBox("Digits");
        CheckBox check4 = new CheckBox("Special Symbols");

        ChoiceBox<Integer> passwordLength = new ChoiceBox<Integer>();
        passwordLength.getItems().addAll(8,9,10,11,12,13,14,15,16);
        passwordLength.setValue(10);

        Button button1 = new Button("Create!");
        Button button2 = new Button("Save me!");
        Button button3 = new Button("Validate!");

        //Actions
        button1.setOnAction(event -> passwordField.setText(create(passwordLength.getValue(), check1.isSelected(), check2.isSelected(), check3.isSelected(), check4.isSelected())));
        button2.setOnAction(event -> saveFile(passwordField.getText()));
        button3.setOnAction(event -> validate(passwordField.getText()));

        //Sub-Layouts
        VBox subLayout = new VBox(10);
        VBox subLayout2 = new VBox(10);
        subLayout.setPadding(new Insets(20, 20, 20, 20));
        subLayout.getChildren().addAll(passwordField, passwordLength, check1, check2, check3, check4);
        subLayout2.setPadding(new Insets(20, 60, 20, 0));
        subLayout2.getChildren().addAll(button1, button3, button2);

        //Main app layout
        BorderPane layout = new BorderPane();
        layout.setLeft(subLayout);
        layout.setRight(subLayout2);

        //Main app scene
        Scene mainScene = new Scene(layout, 300, 450);

        //Stage execution
        mainStage.setTitle("Password Validator");
        mainStage.setScene(mainScene);
        mainStage.show();

    }

    public static String create(int length, boolean check1, boolean check2, boolean check3, boolean check4) {
        //Objects
        StringBuilder pass = new StringBuilder();
        char randomChar;
        Random randomize = new Random();
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";
        String specialSymbols = "!@-+=/";

        //Password Creation in 4 steps
        System.out.println("Validation/Creation");

        for (int i = 0; i < length; i++) {
            pass.insert(i,1);
        }

        if (check1) {
            for (int i = 0; i < length; i++) {
                int tmp = randomize.nextInt(lowerCase.length());
                randomChar = lowerCase.charAt(tmp);
                pass.setCharAt(i, randomChar);
            }
        }
        if (check2) {
            for (int i = 0; i < length; i++) {
                if (randomize.nextBoolean()) {
                    randomChar = upperCase.charAt(randomize.nextInt(upperCase.length()));
                    pass.setCharAt(i, randomChar);
                }
            }
        }
        if (check3) {
            for (int i = 0; i < length; i++) {
                if (randomize.nextBoolean()) {
                    randomChar = numbers.charAt(randomize.nextInt(numbers.length()));
                    pass.setCharAt(i, randomChar);
                }
            }
        }
        if (check4) {
            for (int i = 0; i < length; i++) {
                if (randomize.nextBoolean()) {
                    randomChar = specialSymbols.charAt(randomize.nextInt(specialSymbols.length()));
                    pass.setCharAt(i, randomChar);
                }
            }
        }

        return pass.toString();

    }

    private static void saveFile(String passwordToSave){
        try {
            FileWriter save = new FileWriter("src/main/resources/savedPasswords.txt");
            save.write(passwordToSave);
            save.flush();
            save.close();
        } catch (IOException e) {
            System.out.println("You fucked up bro, 300$");
        }

    }

    private static void validate(String textToValidate) {
        int tmpUpper = 0;
        int tmpLower = 0;
        int tmpDigit = 0;
        int tmpSymbol = 0;

        for (int i = 0; i < textToValidate.length(); i++) {
            if (Character.isUpperCase(textToValidate.charAt(i))) {
                tmpUpper++;
            }
            if (Character.isLowerCase(textToValidate.charAt(i))) {
                tmpLower++;
            }
            if (Character.isDigit(textToValidate.charAt(i))) {
                tmpDigit++;
            }
            if (!Character.isUpperCase(textToValidate.charAt(i)) && !Character.isLowerCase(textToValidate.charAt(i)) && !Character.isDigit(textToValidate.charAt(i))) {
                tmpSymbol++;
            }
        }

        String total = "Password summary: LowerCase: ";
        total = total + tmpLower + " UpperCase: " + tmpUpper + " Digits: " + tmpDigit + " SpecialSymbols: " + tmpSymbol;
        System.out.println(total);
        PopOut.popOut("Summary", total);

    }


}
