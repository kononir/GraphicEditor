package com.bsuir.graphic.editor.listener;

import com.bsuir.graphic.editor.algorithm.AlgorithmController;
import com.bsuir.graphic.editor.algorithm.AlgorithmGroup;
import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.ui.CanvasDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MouseClickListener {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    // how to clear list when switch to other button?
    private static List<CustomPoint> chosenPoints = new ArrayList<>();

    public static void setUp(Canvas canvas, ToggleGroup toggleGroup) {
        canvas.setOnMouseClicked(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            selected.ifPresent(toggle -> {
                AlgorithmType selectedAlgorithm = (AlgorithmType) toggle.getUserData();
                if (AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS.equals(selectedAlgorithm.getGroup())) {
                    chosenPoints.add(new CustomPoint(
                            event.getX(), event.getY(), 0, 0, Color.RED
                    ));

                    drawCurrentPoints(canvas, selectedAlgorithm);
                }
            });
        });
    }

    private static void drawCurrentPoints(Canvas canvas, AlgorithmType selectedAlgorithm) {
        List<CustomPoint> currentPoints = new ArrayList<>(chosenPoints);
        AlgorithmController controller = new AlgorithmController();
        if (AlgorithmType.CIRCLE_GENERATION_ALGORITHM.equals(selectedAlgorithm)
                && currentPoints.size() == 2) {
            currentPoints = controller.controlGeneratingCirclePoints(
                    currentPoints.get(FIRST), currentPoints.get(SECOND)
            );

            chosenPoints.clear();
        } else if (AlgorithmType.ELLIPSE_GENERATION_ALGORITHM.equals(selectedAlgorithm)
                && currentPoints.size() == 3) {
            currentPoints = controller.controlGeneratingEllipsePoints(
                    currentPoints.get(FIRST), currentPoints.get(SECOND), currentPoints.get(THIRD)
            );

            chosenPoints.clear();
        } else {
            // parabola or hyperbole
            currentPoints = null;
        }

        /* drawing */
        CanvasDrawer drawer = new CanvasDrawer(canvas);
        for (CustomPoint point : currentPoints) {
            drawer.drawPoint(point);
        }
    }
}
