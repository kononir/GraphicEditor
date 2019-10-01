package com.bsuir.graphic.editor.listener;

import com.bsuir.graphic.editor.algorithm.AlgorithmController;
import com.bsuir.graphic.editor.algorithm.AlgorithmGroup;
import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.ui.CanvasDrawer;

import java.util.List;
import java.util.Optional;

public class MousePressAndReleaseListener {
    private static double startingMouseX;
    private static double startingMouseY;

    public static void setUp(Canvas canvas, ToggleGroup toggleGroup) {
        canvas.setOnMousePressed(event -> {
            startingMouseX = event.getX();
            startingMouseY = event.getY();
        });

        canvas.setOnMouseReleased(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            selected.ifPresent(toggle -> {
                AlgorithmType selectedAlgorithm = (AlgorithmType) toggle.getUserData();
                if (AlgorithmGroup.LINE_SEGMENT_ALGORITHMS.equals(selectedAlgorithm.getGroup())) {
                    AlgorithmController controller = new AlgorithmController();
                    List<CustomPoint> linePoints = controller.controlGeneratingLineSegmentPoints(
                            selectedAlgorithm,
                            new CustomPoint(startingMouseX, startingMouseY, 0, 0, Color.BLACK),
                            new CustomPoint(event.getX(), event.getY(), 0, 0, Color.BLACK)
                    );

                    /* drawing */
                    CanvasDrawer drawer = new CanvasDrawer(canvas);
                    for (CustomPoint point : linePoints) {
                        drawer.drawPoint(point);
                    }
                }
            });
        });
    }
}
