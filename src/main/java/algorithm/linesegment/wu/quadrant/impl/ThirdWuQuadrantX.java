package algorithm.linesegment.wu.quadrant.impl;

import javafx.scene.paint.Color;
import model.CustomPoint;

import java.util.Arrays;
import java.util.List;

public class ThirdWuQuadrantX extends AbstractWuQuadrant {

    public ThirdWuQuadrantX(int x, int y, int z, int t, double e, double tangentAngle) {
        super(x, y, z, t, e, tangentAngle);
    }

    @Override
    public List<CustomPoint> generateNextPoints() {
        double secondaryY;
        if (e >= 0.5) {
            secondaryY = y;
            y--;
            e--;
        } else {
            secondaryY = y + 1;
        }
        x++;
        e += tangentAngle;

        double intensity = Math.max(Math.min(e, 1.0), 0.0);

        return Arrays.asList(
                new CustomPoint(x, y, z, t, Color.gray(1.0 - intensity)),
                new CustomPoint(x, secondaryY, z, t, Color.gray(intensity))
        );
    }
}
