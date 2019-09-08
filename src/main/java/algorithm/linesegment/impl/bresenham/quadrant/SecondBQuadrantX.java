package algorithm.linesegment.impl.bresenham.quadrant;

import javafx.scene.paint.Color;
import util.CustomPoint;

public class SecondBQuadrantX implements BQuadrant {
    private int x;
    private int y;
    private int z;
    private int t;
    private int e;
    private int deltaX;
    private int deltaY;

    public SecondBQuadrantX(int x, int y, int z, int t, int e, int deltaX, int deltaY) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public CustomPoint calculateNextPoint() {
        if (e >= 0) {
            y++;
            e -= 2 * deltaX;
        }
        x--;
        e += 2 * deltaY;

        return new CustomPoint(x, y, z, t, Color.BLACK);
    }
}
