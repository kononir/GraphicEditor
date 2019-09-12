package com.bsuir.graphic.editor.algorithm.linesegment.bresenham;

import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant.BQuadrant;
import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant.BQuadrantFactory;
import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;

public class BresenhamAlgorithmPointGenerator {
    private int x1;
    private int y1;
    private int z;
    private int t;

    private int initialE;

    private BQuadrant quadrant;

    private int step;
    private int stepsNumber;

    private boolean isSetUp = false;

    public void setUp(CustomPoint startingPoint, CustomPoint endingPoint) {
        x1 = (int) startingPoint.getX();
        int x2 = (int) endingPoint.getX();
        y1 = (int) startingPoint.getY();
        int y2 = (int) endingPoint.getY();
        z = (int) startingPoint.getZ();
        t = (int) startingPoint.getT();

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int projectionX = Math.abs(deltaX);
        int projectionY = Math.abs(deltaY);
        int maxProjection = Math.max(projectionX, projectionY);
        int minProjection = Math.min(projectionX, projectionY);

        initialE = 2 * minProjection - maxProjection;

        step = 0;
        stepsNumber = maxProjection;

        quadrant = new BQuadrantFactory().create(x1, y1, z, t, initialE, deltaX, deltaY);

        isSetUp = true;
    }

    public CustomPoint generateNextPoint() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        CustomPoint point;
        if (step <= stepsNumber) {
            if (step > 0) {
                point = quadrant.generateNextPoint();
            } else {
                point = new CustomPoint(x1, y1, z, t, Color.BLACK);
            }

            step++;
        } else {
            point = null;
        }

        return point;
    }

    public BresenhamAlgorithmDebugInfo generateNextPointDebug() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        BresenhamAlgorithmDebugInfo info;
        if (step <= stepsNumber) {
            if (step > 0) {
                info = quadrant.generateNextPointDebug(step);
            } else {
                info = new BresenhamAlgorithmDebugInfo(
                        step, new CustomPoint(x1, y1, z, t, Color.BLACK), 0, initialE
                );
            }

            step++;
        } else {
            info = null;
        }

        return info;
    }

    public CustomPoint rollbackToPrevPointDebug() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        CustomPoint point;
        if (step >= 1) {
            if (step > 1) {
                point = quadrant.rollbackToPrevPointDebug();
            } else {
                point = new CustomPoint(x1, y1, z, t, Color.BLACK);
            }

            step--;
        } else {
            point = null;
        }

        return point;
    }
}
