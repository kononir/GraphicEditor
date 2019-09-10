package ui.debug;

import javafx.scene.control.Alert;

public class DebugAlert {
    public void show(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText(text);

        alert.show();
    }
}
