package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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

    public void fillCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
