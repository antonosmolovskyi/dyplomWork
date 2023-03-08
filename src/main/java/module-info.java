module com.example.dyplom5_0 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;

    opens com.example.dyplom5_0 to javafx.fxml;
    exports com.example.dyplom5_0;
}