package algorithm.linesegment.wu.quadrant;

import algorithm.linesegment.wu.quadrant.impl.*;

public class WuQuadrantFactory {
    public WuQuadrant create(int x, int y, int z, int t, double tangentAngle, int deltaX, int deltaY) {
        int projectionX = Math.abs(deltaX);
        int projectionY = Math.abs(deltaY);

        boolean positiveX = (deltaX >= 0);
        boolean positiveY = (deltaY >= 0);
        boolean xIsMainCoordinate = (projectionX > projectionY);

        double initialE = tangentAngle;

        WuQuadrant wuQuadrant;
        if (positiveX && positiveY) {
            if (xIsMainCoordinate) {
                wuQuadrant = new FirstWuQuadrantX(x, y, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new FirstWuQuadrantY(x, y, z, t, initialE, tangentAngle);
            }
        } else if (!positiveX && positiveY) {
            if (xIsMainCoordinate) {
                wuQuadrant = new SecondWuQuadrantX(x, y, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new SecondWuQuadrantY(x, y, z, t, initialE, tangentAngle);
            }
        } else if (positiveX) {
            if (xIsMainCoordinate) {
                wuQuadrant = new ThirdWuQuadrantX(x, y, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new ThirdWuQuadrantY(x, y, z, t, initialE, tangentAngle);
            }
        } else {
            if (xIsMainCoordinate) {
                wuQuadrant = new FourthWuQuadrantX(x, y, z, t, initialE, tangentAngle);
            } else {
                wuQuadrant = new FourthWuQuadrantY(x, y, z, t, initialE, tangentAngle);
            }
        }

        return wuQuadrant;
    }
}
