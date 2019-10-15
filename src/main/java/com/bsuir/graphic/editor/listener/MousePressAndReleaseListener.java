package com.bsuir.graphic.editor.listener;

import com.bsuir.graphic.editor.algorithm.AlgorithmController;
import com.bsuir.graphic.editor.algorithm.AlgorithmGroup;
import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.point.CanvasDrawer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MousePressAndReleaseListener {
    private static CanvasDrawer drawer;

    private static CustomPoint first;
    private static List<CustomPoint> previousPoints = new ArrayList<>();

    public static void setUp(Canvas canvas, ToggleGroup toggleGroup, ToggleButton debugButton) {
        drawer = new CanvasDrawer(canvas);

        canvas.setOnMousePressed(event -> {
            first = new CustomPoint(event.getX(), event.getY(), 0, 0, Color.BLACK);
            previousPoints.clear();
        });

        canvas.setOnMouseDragged(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            if (selected.isPresent() && !debugButton.isSelected()) {
                /* clearing previous */
                for (CustomPoint point : previousPoints) {
                    drawer.deletePoint(point);
                }

                CustomPoint second = new CustomPoint(event.getX(), event.getY(), 0, 0, Color.BLACK);
                previousPoints = generateFigure(selected.get(), second);

                /* drawing */
                for (CustomPoint point : previousPoints) {
                    drawer.drawPoint(point);
                }
            }
        });
    }

    private static List<CustomPoint> generateFigure(Toggle toggle, CustomPoint second) {
        List<CustomPoint> figurePoints;

        AlgorithmType selectedAlgorithm = (AlgorithmType) toggle.getUserData();
        AlgorithmController controller = new AlgorithmController();
        if (AlgorithmGroup.LINE_SEGMENT_ALGORITHMS.equals(selectedAlgorithm.getGroup())) {
            figurePoints = controller.controlGeneratingLineSegmentPoints(
                    selectedAlgorithm, first, second
            );
        } else if (AlgorithmType.CIRCLE_GENERATION_ALGORITHM.equals(selectedAlgorithm)) {
            figurePoints = controller.controlGeneratingCirclePoints(first, second);
        } else if (AlgorithmType.ELLIPSE_GENERATION_ALGORITHM.equals(selectedAlgorithm)) {
            figurePoints = controller.controlGeneratingEllipsePoints(first, second);
        } else if (AlgorithmType.HYPERBOLE_GENERATION_ALGORITHM.equals(selectedAlgorithm)) {
            int limit = (int) drawer.getDrawingSpaceHeight();
            figurePoints = controller.controlGeneratingHyperbolePoints(first, second, limit);
        } else {
            figurePoints = Collections.emptyList();
        }

        return figurePoints;
    }
}
