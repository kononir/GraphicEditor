package com.bsuir.graphic.editor.listener;

import com.bsuir.graphic.editor.algorithm.AlgorithmGroup;
import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.ui.CanvasDrawer;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.util.Optional;

public class MousePressAndReleaseListener {
    private static CanvasDrawer drawer;

    private static CustomPoint first;

    public static void setUp(CanvasDrawer drawer, ToggleButton debugButton, ToggleGroup toggleGroup) {
        MousePressAndReleaseListener.drawer = drawer;

        drawer.setOnMousePressed(event ->
                first = CustomPoint.simplePoint(event.getX(), event.getY(), Color.BLACK));

        drawer.setOnMouseDragged(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            if (selected.isPresent() &&  !debugButton.isSelected()) {
                AlgorithmType selectedAlgorithm = (AlgorithmType) selected.get().getUserData();
                AlgorithmGroup algorithmGroup = selectedAlgorithm.getGroup();
                if (AlgorithmGroup.LINE_SEGMENT_ALGORITHMS.equals(algorithmGroup)
                        || (AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS.equals(algorithmGroup))) {
                    drawer.rollbackToPreviousState();
                    CustomPoint second
                            = CustomPoint.simplePoint(event.getX(), event.getY(), Color.BLACK);
                    draw(selectedAlgorithm, second);
                }
            }
        });

        drawer.setOnMouseReleased(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            if (selected.isPresent() && !debugButton.isSelected()) {
                AlgorithmType selectedAlgorithm = (AlgorithmType) selected.get().getUserData();
                AlgorithmGroup algorithmGroup = selectedAlgorithm.getGroup();
                if (AlgorithmGroup.LINE_SEGMENT_ALGORITHMS.equals(algorithmGroup)
                        || (AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS.equals(algorithmGroup))) {
                    drawer.commitCurrentState();
                }
            }
        });
    }

    private static void draw(AlgorithmType selectedAlgorithm, CustomPoint second) {
        switch (selectedAlgorithm) {
            case DDA:
                drawer.drawDdaLineSegment(first, second);
                break;
            case BRESENHAM_ALGORITHM:
                drawer.drawBresenhamLineSegment(first, second);
                break;
            case WU_ALGORITHM:
                drawer.drawWuLineSegment(first, second);
                break;
            case CIRCLE_GENERATION_ALGORITHM:
                drawer.drawCircle(first, second);
                break;
            case ELLIPSE_GENERATION_ALGORITHM:
                drawer.drawEllipse(first, second);
                break;
            case HYPERBOLE_GENERATION_ALGORITHM:
                drawer.drawHyperbole(first, second);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class,
                        selectedAlgorithm.name());
        }
    }
}
