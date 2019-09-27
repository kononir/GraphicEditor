package com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant;

import com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.impl.*;

public class WuQuadrantFactory {
    public WuQuadrant create(int x, int y, int z, int t, double tangentAngle, int deltaX, int deltaY) {
        int projectionX = Math.abs(deltaX);
        int projectionY = Math.abs(deltaY);

        boolean positiveX = (deltaX >= 0);
        boolean positiveY = (deltaY >= 0);
        boolean xIsMainCoordinate = (projectionX > projectionY);

        double initialE = tangentAngle - 0.5;

        WuQuadrant wuQuadrant;
        if (positiveX && positiveY) {
            if (xIsMainCoordinate) {
                wuQuadrant = new FirstWuQuadrantX(x, y + 1, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new FirstWuQuadrantY(x + 1, y, z, t, initialE, tangentAngle);
            }
        } else if (!positiveX && positiveY) {
            if (xIsMainCoordinate) {
                wuQuadrant = new SecondWuQuadrantX(x, y + 1, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new SecondWuQuadrantY(x - 1, y, z, t, initialE, tangentAngle);
            }
        } else if (positiveX) {
            if (xIsMainCoordinate) {
                wuQuadrant = new ThirdWuQuadrantX(x, y - 1, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new ThirdWuQuadrantY(x + 1, y, z, t, initialE, tangentAngle);
            }
        } else {
            if (xIsMainCoordinate) {
                wuQuadrant = new FourthWuQuadrantX(x, y - 1, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new FourthWuQuadrantY(x - 1, y, z, t, initialE, tangentAngle);
            }
        }

        return wuQuadrant;
    }
}
