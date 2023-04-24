module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.jeusio1 to javafx.fxml;
    exports com.example.jeusio1;

}