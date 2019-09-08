package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class DebugComponent {
    private TableView debugTable;
    private Button prevStepButton;
    private Button nextStepButton;

    public DebugComponent(TableView debugTable, Button prevStepButton, Button nextStepButton) {
        this.debugTable = debugTable;
        this.prevStepButton = prevStepButton;
        this.nextStepButton = nextStepButton;
    }

    public void setOnPrevStep(EventHandler<ActionEvent> event) {
        prevStepButton.setOnAction(event);
    }

    public void setOnNextStep(EventHandler<ActionEvent> event) {
        nextStepButton.setOnAction(event);
    }
}
