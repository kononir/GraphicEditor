package com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderlines.specification.EllipseSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class EllipseGenerator extends AbstractFigureGenerator<EllipseSpecification> {
    private static final int LIMIT = 0;

    @Override
    public List<CustomPoint> generate(EllipseSpecification specification) {
        List<CustomPoint> firstQuadrantPoints = generateFirstQuadrant(
                specification.getCenterPoint(), specification.getA(), specification.getB());
        List<CustomPoint> upperSemiEllipsePoints = reflectFirstQuadrantOY(firstQuadrantPoints);
        return reflectUpperFigurePartOX(upperSemiEllipsePoints);
    }

    private List<CustomPoint> generateFirstQuadrant(CustomPoint centerPoint, int a, int b) {
        List<CustomPoint> points = new ArrayList<>();

        int x = (int) centerPoint.getX();
        int y = (int) centerPoint.getY() + b;

        int a2 = a * a;
        int b2 = b * b;

        int e = a2 + b2 - 2 * a2 * b;

        while (y > LIMIT) {
            if (e > 0) {
                y--;

                int delta = 2 * e - 2 * b2 * x - 1;
                if (delta <= 0) {
                    x++;
                    e += 2 * b2 * x + b2 + a2 - 2 * a2 * y;
                } else {
                    e += a2 - 2 * a2 * y;
                }
            } else if (e < 0) {
                x++;

                int delta = 2 * e + 2 * a2 * y - 1;
                if (delta <= 0) {
                    e += 2 * b2 * x + b2;
                } else {
                    y--;
                    e += 2 * b2 * x + b2 + a2 - 2 * a2 * y;
                }
            } else {
                x++;
                y--;
                e += 2 * b2 * x + b2 + a2 - 2 * a2 * y;
            }
        }

        return points;
    }
}
