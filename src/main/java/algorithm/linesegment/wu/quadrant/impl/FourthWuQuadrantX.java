package algorithm.linesegment.wu.quadrant.impl;

import javafx.scene.paint.Color;
import model.CustomPoint;
import algorithm.linesegment.wu.quadrant.WuQuadrant;

import java.util.Arrays;
import java.util.List;

public class FourthWuQuadrantX implements WuQuadrant {
    private int x;
    private int y;
    private int z;
    private int t;
    private double e;
    private double tangentAngle;

    public FourthWuQuadrantX(int x, int y, int z, int t, double e, double tangentAngle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.tangentAngle = tangentAngle;
    }

    @Override
    public List<CustomPoint> calculateNextPoints() {
        double secondaryY;
        if (e >= 0.5) {
            secondaryY = y;
            y--;
            e--;
        } else {
            secondaryY = y + 1;
        }
        x--;
        e += tangentAngle;

        double intensity = Math.max(Math.min(e, 1.0), 0.0);

        return Arrays.asList(
                new CustomPoint(x, y, z, t, Color.gray(1.0 - intensity)),
                new CustomPoint(x, secondaryY, z, t, Color.gray(intensity))
        );
    }
}
