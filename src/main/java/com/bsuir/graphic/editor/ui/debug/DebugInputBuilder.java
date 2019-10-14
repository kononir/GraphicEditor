package com.bsuir.graphic.editor.ui.debug;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DebugInputBuilder {
    private static final int DEBUG_TEXT_FIELD_SIZE = 100;

    public Map<DebugTextFieldType, TextField> build(GridPane debugInputGridPane, AlgorithmType algorithmType) {
        debugInputGridPane.getChildren().clear();

        Map<DebugTextFieldType, TextField> debugTextFieldMap = new EnumMap<>(DebugTextFieldType.class);

        List<Label> labels = new ArrayList<>();
        List<TextField> textFields = new ArrayList<>();
        switch (algorithmType) {
            case DDA:
            case BRESENHAM_ALGORITHM:
            case WU_ALGORITHM:
                Label x1Label = new Label("x1:");
                x1Label.setTextAlignment(TextAlignment.RIGHT);
                labels.add(x1Label);

                TextField x1TextField = new TextField();
                x1TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(x1TextField);
                debugTextFieldMap.put(DebugTextFieldType.X1, x1TextField);

                Label y1Label = new Label("y1:");
                y1Label.setTextAlignment(TextAlignment.RIGHT);
                labels.add(y1Label);

                TextField y1TextField = new TextField();
                y1TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(y1TextField);
                debugTextFieldMap.put(DebugTextFieldType.Y1, y1TextField);

                Label x2Label = new Label("x2:");
                x2Label.setTextAlignment(TextAlignment.RIGHT);
                labels.add(x2Label);

                TextField x2TextField = new TextField();
                x2TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(x2TextField);
                debugTextFieldMap.put(DebugTextFieldType.X2, x2TextField);

                Label y2Label = new Label("y2:");
                y2Label.setTextAlignment(TextAlignment.RIGHT);
                labels.add(y2Label);

                TextField y2TextField = new TextField();
                y2TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(y2TextField);
                debugTextFieldMap.put(DebugTextFieldType.Y2, y2TextField);
                break;
            case CIRCLE_GENERATION_ALGORITHM:
                Label xCircleLabel = new Label("x:");
                xCircleLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(xCircleLabel);

                TextField xCircleTextField = new TextField();
                xCircleTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(xCircleTextField);
                debugTextFieldMap.put(DebugTextFieldType.X, xCircleTextField);

                Label yCircleLabel = new Label("y:");
                yCircleLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(yCircleLabel);

                TextField yCircleTextField = new TextField();
                yCircleTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(yCircleTextField);
                debugTextFieldMap.put(DebugTextFieldType.Y, yCircleTextField);

                Label radiusLabel = new Label("radius:");
                radiusLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(radiusLabel);

                TextField radiusTextField = new TextField();
                radiusTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(radiusTextField);
                debugTextFieldMap.put(DebugTextFieldType.RADIUS, radiusTextField);

                break;
            case ELLIPSE_GENERATION_ALGORITHM:
            case HYPERBOLE_GENERATION_ALGORITHM:
                Label xEllipseHyperboleLabel = new Label("x:");
                xEllipseHyperboleLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(xEllipseHyperboleLabel);

                TextField xEllipseHyperboleTextField = new TextField();
                xEllipseHyperboleTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(xEllipseHyperboleTextField);
                debugTextFieldMap.put(DebugTextFieldType.X, xEllipseHyperboleTextField);

                Label yEllipseHyperboleLabel = new Label("y:");
                yEllipseHyperboleLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(yEllipseHyperboleLabel);

                TextField yEllipseHyperboleTextField = new TextField();
                yEllipseHyperboleTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(yEllipseHyperboleTextField);
                debugTextFieldMap.put(DebugTextFieldType.Y, yEllipseHyperboleTextField);

                Label aLabel = new Label("a:");
                aLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(aLabel);

                TextField aTextField = new TextField();
                aTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(aTextField);
                debugTextFieldMap.put(DebugTextFieldType.A, aTextField);

                Label bLabel = new Label("b:");
                bLabel.setTextAlignment(TextAlignment.RIGHT);
                labels.add(bLabel);

                TextField bTextField = new TextField();
                bTextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
                textFields.add(bTextField);
                debugTextFieldMap.put(DebugTextFieldType.B, bTextField);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
        }

        for(int i = 0; i < labels.size(); i++) {
            debugInputGridPane.add(labels.get(i), i % 2 * 2, i / 2);
        }
        for(int i = 0; i < textFields.size(); i++) {
            debugInputGridPane.add(textFields.get(i), i % 2 * 2 + 1, i / 2);
        }

        return debugTextFieldMap;
    }
}
