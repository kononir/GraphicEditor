package com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.impl;

import javafx.scene.paint.Color;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.Arrays;
import java.util.List;

public class SecondWuQuadrantY extends AbstractWuQuadrant {

    public SecondWuQuadrantY(int x, int y, int z, int t, double e, double tangentAngle) {
        super(x, y, z, t, e, tangentAngle);
    }

    @Override
    public List<CustomPoint> generateNextPoints() {
        double secondaryX;
        if (e >= 0.5) {
            secondaryX = x;
            x--;
            e--;
        } else {
            secondaryX = x + 1;
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
