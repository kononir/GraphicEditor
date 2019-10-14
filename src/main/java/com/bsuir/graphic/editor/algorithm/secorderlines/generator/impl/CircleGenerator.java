package com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderlines.specification.CircleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircleGenerator extends AbstractFigureGenerator<CircleSpecification> {
    @Override
    public List<CustomPoint> generate(CircleSpecification specification) {
        List<CustomPoint> circlePoints;

        CustomPoint center = specification.getCenterPoint();
        int radius = specification.getRadius();
        if (radius > 0) {
            List<CustomPoint> firstQuadrantPoints = generateFirstQuadrant(center, radius);
            List<CustomPoint> upperSemicirclePoints = reflectFirstQuadrantOY(firstQuadrantPoints);
            circlePoints = reflectUpperFigurePartOX(upperSemicirclePoints);
        } else {
            circlePoints = Collections.singletonList(center);
        }

        return circlePoints;
    }

    private List<CustomPoint> generateFirstQuadrant(CustomPoint centerPoint, int radius) {
        List<CustomPoint> points = new ArrayList<>();

        int x = 0;
        int y = radius;
        int e = 2 - 2 * radius;

        points.add(new CustomPoint(x + centerPoint.getX(), y + centerPoint.getY(), 0, 0, Color.GREEN));

        int limit = 0;
        while (y > limit) {
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

            points.add(new CustomPoint(x + centerPoint.getX(), y + centerPoint.getY(), 0, 0, Color.RED));
        }

        return points;
    }
}
