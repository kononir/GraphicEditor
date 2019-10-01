package com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderlines.specification.CircleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CircleGenerator extends AbstractFigureGenerator<CircleSpecification> {
    private static final int LIMIT = 0;

    @Override
    public List<CustomPoint> generate(CircleSpecification specification) {
        List<CustomPoint> firstOctantPoints = generateFirstOctant(
                specification.getCenterPoint(), specification.getRadius());
        List<CustomPoint> firstQuadrantPoints = reflectFirstOctantXY(firstOctantPoints);
        List<CustomPoint> upperSemicirclePoints = reflectFirstQuadrantOY(firstQuadrantPoints);
        return reflectUpperFigurePartOX(upperSemicirclePoints);
    }

    private List<CustomPoint> generateFirstOctant(CustomPoint centerPoint, int radius) {
        List<CustomPoint> points = new ArrayList<>();

        // begin - x and y start coordinate of first octant
        int begin = (int) (radius / Math.sqrt(2));
        int x = (int) centerPoint.getX() + begin;
        int y = (int) centerPoint.getY() + begin;
        int e = 2 - 2 * radius;

        points.add(new CustomPoint(x, y, 0, 0, Color.BLACK));

        while (y > LIMIT) {
            if (e > 0) {
                int delta = 2 * e - 2 * x - 1;
                if (delta > 0) {
                    y--;
                    e = e - 2 * y + 1;
                } else {
                    x++;
                    y--;
                    e = e + 2 * x - 2 * y + 2;
                }
            } else if (e < 0) {
                int delta = 2 * e + 2 * y - 1;
                if (delta <= 0) {
                    x++;
                    e = e + 2 * x + 1;
                } else {
                    x++;
                    y--;
                    e = e + 2 * x - 2 * y + 2;
                }
            } else {
                x++;
                y--;
                e = e + 2 * x - 2 * y + 2;
            }

            points.add(new CustomPoint(x, y, 0, 0, Color.BLACK));
        }

        return points;
    }
}
