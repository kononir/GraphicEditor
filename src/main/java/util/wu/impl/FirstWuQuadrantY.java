package util.wu.impl;

import javafx.scene.paint.Color;
import model.CustomPoint;
import util.wu.WuQuadrant;

import java.util.Arrays;
import java.util.List;

public class FirstWuQuadrantY implements WuQuadrant {
    private int x;
    private int y;
    private int z;
    private int t;
    private double e;
    private double tangentAngle;

    public FirstWuQuadrantY(int x, int y, int z, int t, double e, double tangentAngle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.tangentAngle = tangentAngle;
    }

    @Override
    public List<CustomPoint> calculateNextPoints() {
        double secondaryX;
        if (e >= 0.5) {
            secondaryX = x;
            x++;
            e--;
        } else {
            secondaryX = x - 1;
        }
        y++;
        e += tangentAngle;

        double intensity = Math.max(Math.min(e, 1.0), 0.0);

        return Arrays.asList(
                new CustomPoint(x, y, z, t, Color.gray(1.0 - intensity)),
                new CustomPoint(secondaryX, y, z, t, Color.gray(intensity))
        );
    }
}
