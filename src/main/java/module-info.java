module juego.anggie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens juego.anggie to javafx.fxml;
    exports juego.anggie;

}