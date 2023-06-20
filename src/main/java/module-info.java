module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens pack.calculator to javafx.fxml;
    exports pack.calculator;
}