package com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderlines.specification.HyperboleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HyperboleGenerator extends AbstractFigureGenerator<HyperboleSpecification> {
    @Override
    public List<CustomPoint> generate(HyperboleSpecification specification) {
        CustomPoint center = specification.getCenterPoint();
        int a = specification.getA();
        int b = specification.getB();

        if (a != 0 && b != 0) {
            List<CustomPoint> oneFourth = generateOneFourth(center, a, b, specification.getLimit());
            List<CustomPoint> half = reflectOX(oneFourth, center);
            List<CustomPoint> figure = reflectOY(half, center);
            return figure;
        } else {
            return Collections.singletonList(center);
        }
    }

    private List<CustomPoint> generateOneFourth(CustomPoint centerPoint, int a, int b, int limit) {
        List<CustomPoint> points = new ArrayList<>();

        int x = a;
        int y = 0;

        int a2 = a * a;
        int b2 = b * b;

        int e = 2 * a * b2 + b2 - a2;

        points.add(CustomPoint.simplePoint(x + centerPoint.getX(), y + centerPoint.getY(), Color.BLACK));

        while (y < limit) {
            if (e > 0) {
                y++;

                int delta = 2 * e - b2 - 2 * b2 * x;
                if (delta <= 0) {
                    x++;
                    e += 2 * b2 * x + b2 - 2 * a2 * y - a2;
                } else {
                    e += -a2 - 2 * a2 * y;
                }
            } else if (e < 0) {
                x++;

                int delta = 2 * e + a2 + 2 * a2 * y;
                if (delta <= 0) {
                    e += 2 * b2 * x + b2;
                } else {
                    y++;
                    e += 2 * b2 * x + b2 - 2 * a2 * y - a2;
                }
            } else {
                x++;
                y++;
                e += 2 * b2 * x + b2 - 2 * a2 * y - a2;
            }

            points.add(CustomPoint.simplePoint(x + centerPoint.getX(), y + centerPoint.getY(), Color.BLACK));
        }

        return points;
    }
}
