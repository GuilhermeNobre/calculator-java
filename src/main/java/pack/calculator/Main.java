package pack.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pack.operations.Operations;

import java.io.IOException;

public class Main extends Application {
    private TextField textField = new TextField();
    private long num1 = 0;
    private String op= "";
    private boolean start = true;

    @Override
    public void start(Stage stage) throws IOException {
        textField.setFont(Font.font(20));
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setEditable(false);

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(10));
        stackPane.getChildren().add(textField);

        TilePane title = new TilePane();
        title.setHgap(10);
        title.setVgap(10);
        title.setAlignment(Pos.TOP_CENTER);
        title.setCursor(Cursor.HAND);
        title.getChildren().addAll(
                createButtonForNumber("7"),
                createButtonForNumber("8"),
                createButtonForNumber("9"),
                createButtonForOperators("/"),
                createButtonForOperators("x²"),

                createButtonForNumber("4"),
                createButtonForNumber("5"),
                createButtonForNumber("6"),
                createButtonForOperators("X"),
                createButtonForOperators("xY"),

                createButtonForNumber("1"),
                createButtonForNumber("2"),
                createButtonForNumber("3"),
                createButtonForOperators("-"),
                createButtonForOperators("√"),

                createButtonForNumber("0"),
                createButtonForClear("C"),
                createButtonForOperators("="),
                createButtonForOperators("+"),
                createButtonForOperators("y√")
                );

        BorderPane root = new BorderPane();
        root.setTop(stackPane);
        root.setCenter(title);
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.setTitle("Calculadora");
        stage.setResizable(false);
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }
    private Button createButtonForNumber (String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction(this::processNumbers);
        return button;
    }
    private Button createButtonForOperators (String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction(this::processOperators);
        return button;
    }
    private Button createButtonForClear (String ch) {
        Button button = new Button(ch);
        button.setFont(Font.font(18));
        button.setPrefSize(50,50);
        button.setOnAction( e -> {
            textField.setText("");
            op = "";
            start = true;
        });
        return button;
    }

    private void processNumbers(ActionEvent e) {
        if(start) {
         textField.setText("");
         start = false;
        }
        textField.setText(textField.getText() + ((Button)e.getSource()).getText());
    }
    private void processOperators(ActionEvent e) {
        String value = ((Button)e.getSource()).getText();

        if (value.equals("x²"))
        {
            num1 = Long.parseLong(textField.getText());
            textField.setText(String.valueOf(calculate(num1, 0,"x²")));
            start = true;
            op = "";
        }

        if (value.equals("√"))
        {
            num1 = Long.parseLong(textField.getText());
            textField.setText(String.valueOf(calculate(num1, 0,"√")));
            start = true;
            op = "";
        }

        if(!value.equals("=")) {
            if (!op.isEmpty()) return;
            num1 = Long.parseLong(textField.getText());
            op = value;
            textField.setText("");
        }
        else {
            if (op.isEmpty()) return;
            long num2 = Long.parseLong(textField.getText());
            textField.setText(String.valueOf(calculate(num1, num2, op)));
            start = true;
            op = "";
        }
    }
    private float calculate(long num1, long num2, String operator) {
        Operations op = new Operations();
        switch (operator) {
            case "+": return op.sum(num1, num2);
            case "-": return op.subtraction(num1, num2);
            case "X": return op.multiplie(num1, num2);
            case "/": {
                if (num2 == 0) return 0;
                return (float)op.divide(num1, num2);
            }
            case "x²": return op.pow(num1);
            case "xY": return op.powCustom(num1, num2);
            case "√":  return op.squareRoot(num1);
            case "y√": return op.customRoot(num1, num2);

            default: return 0;

        }
    }
    public static void main(String[] args) {
        launch();
    }
}