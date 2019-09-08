package algorithm.linesegment.impl.bresenham.quadrant;

import javafx.scene.paint.Color;
import util.CustomPoint;

public class FirstBQuadrantX implements BQuadrant {
    private int x;
    private int y;
    private int z;
    private int t;
    private int e;
    private int maxProjection;
    private int minProjection;

    public FirstBQuadrantX(int x, int y, int z, int t, int e, int maxProjection, int minProjection) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.maxProjection = maxProjection;
        this.minProjection = minProjection;
    }

    @Override
    public CustomPoint calculateNextPoint() {
        if (e >= 0) {
            y++;
            e -= 2 * maxProjection; // percents: e / (2 * maxProjection) + 0.5
        }
        x++;
        e += 2 * minProjection;

        return new CustomPoint(x, y, z, t, Color.BLACK);
    }
}
