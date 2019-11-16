package com.bsuir.graphic.editor.util.ui;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.controler.AlgorithmController;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CanvasDrawer {
    public static final int BIG_POINT_SIZE = 10;

    Set<CustomPoint> currentState = new HashSet<>();
    Set<CustomPoint> previousState = new HashSet<>();

    private Canvas canvas;
    private Pane actionPane;

    private AlgorithmController algorithmController = new AlgorithmController();

    public CanvasDrawer(Canvas canvas, Pane actionPane) {
        this.canvas = canvas;
        this.actionPane = actionPane;
    }

    public void drawPoint(CustomPoint point) {
        canvas.getGraphicsContext2D().getPixelWriter().setColor(
                (int) point.getX(), (int) point.getY(), point.getColor()
        );
    }

    public void drawPoints(List<CustomPoint> points) {
        for (CustomPoint point : points) {
            drawPoint(point);
        }
    }

    public void drawBigPoint(CustomPoint point) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(point.getColor());

        double firstX = (int) point.getX() * BIG_POINT_SIZE;
        double firstY = (int) point.getY() * BIG_POINT_SIZE;
        gc.fillRect(firstX, firstY, BIG_POINT_SIZE, BIG_POINT_SIZE);
    }

    public void deletePoint(CustomPoint point) {
        drawPoint(new CustomPoint(point.getX(), point.getY(), point.getZ(), point.getT(),
                Color.WHITE));
    }

    public void deleteBigPoint(CustomPoint point) {
        drawBigPoint(new CustomPoint(point.getX(), point.getY(), point.getZ(), point.getT(),
                Color.WHITE));
    }

    public void clear() {
        fillCanvas();

        actionPane.getChildren().clear();

        previousState.clear();
        currentState.clear();
    }

    private void fillCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawGrid(int gridSpacing) {
        int canvasWidth = (int) canvas.getWidth();
        int canvasHeight = (int) canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int x = 0; x < canvasWidth; x += gridSpacing) {
            gc.strokeLine(x, 0, x, canvasHeight);
        }
        for (int y = 0; y < canvasHeight; y += gridSpacing) {
            gc.strokeLine(0, y, canvasWidth, y);
        }
    }

    public void commitCurrentState() {
        previousState.addAll(currentState);
        currentState.clear();
    }

    private void saveLastOperation(List<CustomPoint> operationResults) {
        currentState.addAll(previousState);
        currentState.addAll(operationResults);
    }

    public void rollbackToPreviousState() {
        fillCanvas();
        drawPoints(new ArrayList<>(previousState));
        currentState.clear();
    }

    public void drawDdaLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingLineSegmentPoints(
                AlgorithmType.DDA, startingPoint, endingPoint);
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawBresenhamLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingLineSegmentPoints(
                AlgorithmType.BRESENHAM_ALGORITHM, startingPoint, endingPoint);
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawWuLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingLineSegmentPoints(
                AlgorithmType.WU_ALGORITHM, startingPoint, endingPoint);
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawCircle(CustomPoint centerPoint, CustomPoint radiusPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingCirclePoints(centerPoint,
                radiusPoint);
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawEllipse(CustomPoint centerPoint, CustomPoint radiusPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingEllipsePoints(centerPoint,
                radiusPoint);
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawHyperbole(CustomPoint centerPoint, CustomPoint radiusPoint) {
        List<CustomPoint> points = algorithmController.controlGeneratingHyperbolePoints(centerPoint,
                radiusPoint, (int) getDrawingSpaceHeight());
        drawPoints(points);
        saveLastOperation(points);
    }

    public void drawCubeSpline(List<CustomPoint> root) {
        List<CustomPoint> points = algorithmController.controlGeneratingCubeSpline(root);
        drawPolygonalLine(points);
    }

    public void drawBezierShape(List<CustomPoint> root) {
        List<CustomPoint> points = algorithmController.controlGeneratingBezierShape(root);
        drawPolygonalLine(points);
    }

    private void drawPolygonalLine(List<CustomPoint> points) {
        if (points.size() < 2) {
            throw new IllegalArgumentException("To draw polygonal line must be more than 2 points!");
        }

        CustomPoint first = points.get(0);
        int lastIndex = points.size() - 1;
        CustomPoint last = points.get(lastIndex);

        if (points.size() == 2) {
            drawWuLineSegment(first, last);
        } else {
            CustomPoint previous = first;
            for (CustomPoint current : points) {
                drawWuLineSegment(previous, current);
                previous = current;
            }
        }
    }

    public double getDrawingSpaceWidth() {
        return canvas.getWidth();
    }

    public double getDrawingSpaceHeight() {
        return canvas.getHeight();
    }

    public void drawShowedPoint(Circle circle) {
        actionPane.getChildren().add(circle);
    }

    public void removeShowedPoint(Circle circle) {
        actionPane.getChildren().remove(circle);
    }

    public List<Circle> getShowedPoints() {
        ObservableList<Node> nodes = actionPane.getChildren();

        List<Circle> circles = new ArrayList<>();
        for (Node node : nodes) {
            circles.add((Circle) node);
        }

        return circles;
    }

    public void removeAllShowedPoints() {
        actionPane.getChildren().clear();
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> handler) {
        actionPane.setOnMouseClicked(handler);
    }

    public void setOnMouseDragged(EventHandler<? super MouseEvent> handler) {
        actionPane.setOnMouseDragged(handler);
    }

    public void setOnMousePressed(EventHandler<? super MouseEvent> handler) {
        actionPane.setOnMousePressed(handler);
    }

    public void setOnMouseReleased(EventHandler<? super MouseEvent> handler) {
        actionPane.setOnMouseReleased(handler);
    }
}
