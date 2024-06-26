module gioco.angolo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens gioco.angolo to javafx.fxml;
    exports gioco.angolo;
    exports gioco.angolo.table;
    opens gioco.angolo.table to javafx.fxml;
    exports gioco.angolo.angle;
    opens gioco.angolo.angle to javafx.fxml;
    exports gioco.angolo.difficolta;
    opens gioco.angolo.difficolta to javafx.fxml;
}