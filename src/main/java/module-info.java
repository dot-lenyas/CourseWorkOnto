module com.example.courseworkonto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.courseworkonto to javafx.fxml;
    exports com.example.courseworkonto;
}