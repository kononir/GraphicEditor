package com.bsuir.graphic.editor.listener;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.ui.CanvasDrawer;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MouseDraggablePointsClickListener {
    private static final int MIN_NUMBER = 4;

    private static boolean isCircular = false;

    private static CanvasDrawer drawer;

    public static void setUp(CanvasDrawer drawer, ToggleGroup toggleGroup) {
        MouseDraggablePointsClickListener.drawer = drawer;

        drawer.setOnMouseClicked(clickEvent -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            selected.ifPresent(toggle -> {
                AlgorithmType algorithmType = (AlgorithmType) toggle.getUserData();
                MouseButton button = clickEvent.getButton();
                if (clickEvent.getClickCount() == 2 && MouseButton.PRIMARY.equals(button)) {
                    List<Circle> points = drawer.getShowedPoints();
                    int pointsNumber = points.size() + 1;

                    switch (algorithmType) {
                        case HERMIT_INTERPOLATION_ALGORITHM:
                        case BEZIER_INTERPOLATION_ALGORITHM:
                            if (pointsNumber <= MIN_NUMBER) {
                                Circle undraggable = createNewUndraggablePoint(clickEvent.getX(),
                                        clickEvent.getY());
                                points.add(undraggable);
                            }

                            if (pointsNumber == MIN_NUMBER) {
                                for (Circle point : points) {
                                    createPointDraggable(point, algorithmType);
                                }

                                draw(algorithmType);
                            }

                            break;
                        case B_SPLINE_EXTRAPOLATION_ALGORITHM:
                            Circle undraggable = createNewUndraggablePoint(clickEvent.getX(),
                                    clickEvent.getY());
                            points.add(undraggable);

                            if (pointsNumber == MIN_NUMBER) {
                                for (Circle point : points) {
                                    createPointDraggable(point, algorithmType);
                                }

                                draw(algorithmType);
                            } else if (pointsNumber > MIN_NUMBER) {
                                drawer.rollbackToPreviousState();

                                createPointDraggable(undraggable, algorithmType);

                                draw(algorithmType);
                            }

                            break;
                    }
                } else if (MouseButton.SECONDARY.equals(button)) {
                    drawer.commitCurrentState();
                    drawer.removeAllShowedPoints();
                } else if (MouseButton.MIDDLE.equals(clickEvent.getButton())
                        && AlgorithmType.B_SPLINE_EXTRAPOLATION_ALGORITHM.equals(algorithmType)
                        && drawer.getShowedPoints().size() >= MIN_NUMBER) {
                    isCircular = !isCircular;

                    drawer.rollbackToPreviousState();
                    draw(algorithmType);
                }
            });
        });
    }

    private static Circle createNewUndraggablePoint(double x, double y) {
        Circle undraggable = new Circle(x, y, 5, Color.RED);
        drawer.drawShowedPoint(undraggable);

        return undraggable;
    }

    private static void createPointDraggable(Circle undraggable, AlgorithmType algorithmType) {
        drawer.removeShowedPoint(undraggable);

        Circle draggable = new Circle(undraggable.getCenterX(),
                undraggable.getCenterY(), 8, Color.GREEN);
        draggable.setOnMouseDragged(dragEvent -> {
            double newX = dragEvent.getX();
            double newY = dragEvent.getY();

            draggable.setCenterX(newX);
            draggable.setCenterY(newY);

            drawer.rollbackToPreviousState();

            draw(algorithmType);
        });

        drawer.drawShowedPoint(draggable);
    }

    private static void draw(AlgorithmType algorithmType) {
        List<Circle> points = drawer.getShowedPoints();
        List<CustomPoint> root = new ArrayList<>();
        for (Circle undraggable : points) {
            root.add(CustomPoint.simplePoint(undraggable.getCenterX(),
                    undraggable.getCenterY(), Color.BLACK));
        }

        switch (algorithmType) {
            case HERMIT_INTERPOLATION_ALGORITHM:
                drawer.drawCubeSpline(root);
                break;
            case BEZIER_INTERPOLATION_ALGORITHM:
                drawer.drawBezierShape(root);
                break;
            case B_SPLINE_EXTRAPOLATION_ALGORITHM:
                drawer.drawBSpline(root, isCircular);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.name());
        }
    }
}
