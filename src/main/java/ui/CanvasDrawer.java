package ui;

import javafx.scene.canvas.Canvas;
import model.CustomPoint;

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
}
