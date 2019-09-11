package algorithm.linesegment.bresenham.quadrant.impl;

import javafx.scene.paint.Color;
import model.CustomPoint;
import algorithm.linesegment.bresenham.quadrant.BQuadrant;

public class ThirdBQuadrantY extends AbstractBQuadrant {

    public ThirdBQuadrantY(int x, int y, int z, int t, int e, int maxProjection, int minProjection) {
        super(x, y, z, t, e, maxProjection, minProjection);
    }

    @Override
    public CustomPoint generateNextPoint() {
        if (e >= 0) {
            x++;
            e -= 2 * maxProjection;
        }
        y--;
        e += 2 * minProjection;

        return new CustomPoint(x, y, z, t, Color.BLACK);
    }
}
