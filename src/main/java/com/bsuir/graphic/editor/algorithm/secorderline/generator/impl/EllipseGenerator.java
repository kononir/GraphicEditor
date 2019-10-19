package com.bsuir.graphic.editor.algorithm.secorderline.generator.impl;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.BresenhamAlgorithm;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.EllipseSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EllipseGenerator extends AbstractFigureGenerator<EllipseSpecification> {
    private LineSegmentAlgorithm lineSegmentAlgorithm = new BresenhamAlgorithm();

    @Override
    public List<CustomPoint> generate(EllipseSpecification specification) {
        List<CustomPoint> ellipsePoints;

        CustomPoint center = specification.getCenterPoint();
        int a = specification.getA();
        int b = specification.getB();
        if (a != 0 && b != 0) {
            List<CustomPoint> firstQuadrantPoints = generateFirstQuadrant(center, a, b);
            List<CustomPoint> upperSemiEllipsePoints = reflectOY(firstQuadrantPoints, center);
            ellipsePoints = reflectOX(upperSemiEllipsePoints, center);
        } else if (a == 0 && b != 0) {
            CustomPoint right = CustomPoint.simplePoint(center.getX(), center.getY() + b, Color.BLACK);
            List<CustomPoint> rightLineSegmentPart = lineSegmentAlgorithm.generateLineSegment(center, right);
            ellipsePoints = reflectOX(rightLineSegmentPart, center);
        } else {
            CustomPoint upper = CustomPoint.simplePoint(center.getX() + a, center.getY(), Color.BLACK);
            List<CustomPoint> upperLineSegmentPart = lineSegmentAlgorithm.generateLineSegment(center, upper);
            ellipsePoints = reflectOY(upperLineSegmentPart, center);
        }

        return ellipsePoints;
    }

    private List<CustomPoint> generateFirstQuadrant(CustomPoint centerPoint, int a, int b) {
        List<CustomPoint> points = new ArrayList<>();

        int x = 0;
        int y = b;

        int a2 = a * a;
        int b2 = b * b;

        int e = a2 + b2 - 2 * a2 * b;

        points.add(new CustomPoint(x + centerPoint.getX(), y + centerPoint.getY(), 0, 0, Color.BLACK));

        int limit = 0;
        while (y > limit) {
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

            points.add(new CustomPoint(x + centerPoint.getX(), y + centerPoint.getY(), 0, 0, Color.BLACK));
        }

        return points;
    }
}
