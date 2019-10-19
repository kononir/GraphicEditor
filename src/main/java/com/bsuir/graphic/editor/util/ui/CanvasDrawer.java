package com.bsuir.graphic.editor.util.ui;

import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasDrawer {
    public static final int BIG_POINT_SIZE = 10;

    private Canvas canvas;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void drawPoint(CustomPoint point) {
        canvas.getGraphicsContext2D().getPixelWriter().setColor(
                (int) point.getX(), (int) point.getY(), point.getColor()
        );
    }

    public void drawBigPoint(CustomPoint point) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(point.getColor());

        double firstX = (int) point.getX() * BIG_POINT_SIZE;
        double firstY = (int) point.getY() * BIG_POINT_SIZE;
        gc.fillRect(firstX, firstY, BIG_POINT_SIZE, BIG_POINT_SIZE);
    }

    public void deletePoint(CustomPoint point) {
        drawPoint(new CustomPoint(point.getX(), point.getY(), point.getZ(), point.getT(), Color.WHITE));
    }

    public void deleteBigPoint(CustomPoint point) {
        drawBigPoint(new CustomPoint(point.getX(), point.getY(), point.getZ(), point.getT(), Color.WHITE));
    }

    public void fillCanvas() {
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

    public double getDrawingSpaceWidth() {
        return canvas.getWidth();
    }

    public double getDrawingSpaceHeight() {
        return canvas.getHeight();
    }
}
