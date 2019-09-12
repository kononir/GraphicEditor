package com.bsuir.graphic.editor.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;

public class CanvasDrawer {
    private Canvas canvas;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void drawPoint(CustomPoint point) {
        canvas.getGraphicsContext2D().getPixelWriter().setColor(
                (int) point.getX(), (int) point.getY(), point.getColor()
        );
    }

    public void deletePoint(CustomPoint point) {
        drawPoint(new CustomPoint(point.getX(), point.getY(), point.getZ(), point.getT(), Color.WHITE));
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

    public void setRightAnchor(double anchor) {
        AnchorPane.setRightAnchor(canvas, anchor);
    }
}
