package com.bsuir.graphic.editor.algorithm.linesegment.wu;

import com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.WuQuadrant;
import com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.WuQuadrantFactory;
import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;

import java.util.Collections;
import java.util.List;

public class WuAlgorithmPointGenerator {
    private int x1;
    private int y1;
    private int z;
    private int t;

    private double tangentAngle;

    private WuQuadrant quadrant;

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

        double maxProjection = Math.max(projectionX, projectionY);
        double minProjection = Math.min(projectionX, projectionY);

        tangentAngle = minProjection / maxProjection;

        step = 0;
        stepsNumber = (int) maxProjection;

        quadrant = new WuQuadrantFactory().create(x1, y1, z, t, tangentAngle, deltaX, deltaY);

        isSetUp = true;
    }

    public List<CustomPoint> generateNextPoints() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        List<CustomPoint> points;
        if (step <= stepsNumber) {
            if (step > 0) {
                points = quadrant.generateNextPoints();
            } else {
                points = Collections.singletonList(new CustomPoint(x1, y1, z, t, Color.BLACK));
            }

            step++;
        } else {
            points = null;
        }

        return points;
    }

    public WuAlgorithmDebugInfo generateNextPointDebug() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        WuAlgorithmDebugInfo info;
        if (step <= stepsNumber) {
            if (step > 0) {
                info = quadrant.generateNextPointsDebug(step);
            } else {
                info = new WuAlgorithmDebugInfo(
                        step,
                        new CustomPoint(x1, y1, z, t, Color.BLACK),
                        null,
                        0,
                        tangentAngle
                );
            }

            step++;
        } else {
            info = null;
        }

        return info;
    }

    public List<CustomPoint> rollbackToPrevPointDebug() {
        if (!isSetUp) {
            throw new IllegalStateException("Set up initial values before calling generation");
        }

        List<CustomPoint> points;
        if (step >= 1) {
            if (step > 1) {
                points = quadrant.rollbackToPrevPointsDebug();
            } else {
                points = Collections.singletonList(new CustomPoint(x1, y1, z, t, Color.BLACK));
            }

            step--;
        } else {
            points = null;
        }

        return points;
    }
}
