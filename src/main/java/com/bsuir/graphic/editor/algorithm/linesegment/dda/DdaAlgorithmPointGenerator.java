package com.bsuir.graphic.editor.algorithm.linesegment.dda;

import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.DdaAlgorithmDebugInfo;

public class DdaAlgorithmPointGenerator {
    private double x;
    private double y;
    private double z;
    private double t;
    private double dx;
    private double dy;

    private int step;
    private int stepsNumber;

    private boolean isSetUp = false;

    public void setUp(CustomPoint startingPoint, CustomPoint endingPoint) {
        double x1 = startingPoint.getX();
        double x2 = endingPoint.getX();
        double y1 = startingPoint.getY();
        double y2 = endingPoint.getY();

        x = x1;
        y = y1;
        z = startingPoint.getZ();
        t = startingPoint.getT();

        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        double projectionX = Math.abs(deltaX);
        double projectionY = Math.abs(deltaY);
        double maxProjection = Math.max(projectionX, projectionY);

        dx = deltaX / maxProjection;
        dy = deltaY / maxProjection;

        step = 0;
        stepsNumber = (int) maxProjection + 1;

        isSetUp = true;
    }

    public CustomPoint generateNextPoint() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        CustomPoint point;
        if (step <= stepsNumber) {
            if (step == 0) {
                x += 0.5 * Math.signum(dx);
                y += 0.5 * Math.signum(dy);
            } else {
                x += dx;
                y += dy;
            }

            step++;

            point = new CustomPoint(x, y, z, t, Color.BLACK);
        } else {
            point = null;
        }

        return point;
    }

    public DdaAlgorithmDebugInfo generateNextPointDebug() {
        return new DdaAlgorithmDebugInfo(step, generateNextPoint());
    }

    public CustomPoint rollbackToPrevPointDebug() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling rollback");
        }

        CustomPoint currPoint;
        if (step > 0) {
            currPoint = new CustomPoint(x, y, z, t, Color.BLACK);
            if (step == 1) {
                x -= 0.5 * Math.signum(dx);
                y -= 0.5 * Math.signum(dy);
            } else {
                x -= dx;
                y -= dy;
            }

            step--;
        } else {
            currPoint = null;
        }

        return currPoint;
    }
}
