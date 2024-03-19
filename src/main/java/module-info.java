module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment.dictionary to javafx.fxml;
    exports assignment.dictionary;
}