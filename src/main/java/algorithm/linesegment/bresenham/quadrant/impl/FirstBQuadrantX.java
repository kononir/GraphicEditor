package algorithm.linesegment.bresenham.quadrant.impl;

import javafx.scene.paint.Color;
import model.CustomPoint;

public class FirstBQuadrantX extends AbstractBQuadrant {

    public FirstBQuadrantX(int x, int y, int z, int t, int e, int maxProjection, int minProjection) {
        super(x, y, z, t, e, maxProjection, minProjection);
    }

    @Override
    public CustomPoint generateNextPoint() {
        if (e >= 0) {
            y++;
            e -= 2 * maxProjection;
        }
        x++;
        e += 2 * minProjection;

        return new CustomPoint(x, y, z, t, Color.BLACK);
    }
}
