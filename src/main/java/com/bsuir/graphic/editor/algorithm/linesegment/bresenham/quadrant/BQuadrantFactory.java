package com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant;

import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant.impl.*;

public class BQuadrantFactory {
    public BQuadrant create(int x, int y, int z, int t, int e, int deltaX, int deltaY) {
        int projectionX = Math.abs(deltaX);
        int projectionY = Math.abs(deltaY);

        int maxProjection = Math.max(projectionX, projectionY);
        int minProjection = Math.min(projectionX, projectionY);

        boolean positiveX = (deltaX >= 0);
        boolean positiveY = (deltaY >= 0);
        boolean xIsMainCoordinate = (projectionX > projectionY);

        BQuadrant bQuadrant;
        if (positiveX && positiveY) {
            if (xIsMainCoordinate) {
                bQuadrant = new FirstBQuadrantX(x, y, z, t, e, maxProjection, minProjection);
            } else {
                bQuadrant = new FirstBQuadrantY(x, y, z, t, e, maxProjection, minProjection);
            }
        } else if (!positiveX && positiveY) {
            if (xIsMainCoordinate) {
                bQuadrant = new SecondBQuadrantX(x, y, z, t, e, maxProjection, minProjection);
            } else {
                bQuadrant = new SecondBQuadrantY(x, y, z, t, e, maxProjection, minProjection);
            }
        } else if (positiveX) {
            if (xIsMainCoordinate) {
                bQuadrant = new ThirdBQuadrantX(x, y, z, t, e, maxProjection, minProjection);
            } else {
                bQuadrant = new ThirdBQuadrantY(x, y, z, t, e, maxProjection, minProjection);
            }
        } else {
            if (xIsMainCoordinate) {
                bQuadrant = new FourthBQuadrantX(x, y, z, t, e, maxProjection, minProjection);
            } else {
                bQuadrant = new FourthBQuadrantY(x, y, z, t, e, maxProjection, minProjection);
            }
        }

        return bQuadrant;
    }
}
