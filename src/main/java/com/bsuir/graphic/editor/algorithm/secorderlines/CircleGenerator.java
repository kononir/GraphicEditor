package com.bsuir.graphic.editor.algorithm.secorderlines;

import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CircleGenerator {
    private static final int LIMIT = 0;

    public List<CustomPoint> generate(CustomPoint centerPoint, int radius) {
        List<CustomPoint> upperSemicirclePoints = generateUpperSemicircle(centerPoint, radius);

        int shiftSize = 1;
        int lastPointIndex = upperSemicirclePoints.size() - 1;

        List<CustomPoint> lowerSemicirclePoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint upperSemicirclePoint = upperSemicirclePoints.get(pointIndex);
            int x = (int) upperSemicirclePoint.getX();
            int y = (int) upperSemicirclePoint.getY();
            lowerSemicirclePoints.add(new CustomPoint(x, y - shiftSize, 0, 0, Color.BLACK));

            CustomPoint nextUpperSemicirclePoint = upperSemicirclePoints.get(pointIndex + 1);
            int nextX = (int) nextUpperSemicirclePoint.getX();
            int nextY= (int) nextUpperSemicirclePoint.getY();
            shiftSize += nextY - y;
        }

        CustomPoint upperSemicirclePoint = upperSemicirclePoints.get(lastPointIndex);
        int x = (int) upperSemicirclePoint.getX();
        int y = (int) upperSemicirclePoint.getY();
        lowerSemicirclePoints.add(new CustomPoint(x, y - shiftSize, 0, 0, Color.BLACK));

        List<CustomPoint> circlePoints = new ArrayList<>();
        circlePoints.addAll(upperSemicirclePoints);
        circlePoints.addAll(lowerSemicirclePoints);

        return circlePoints;
    }

    private List<CustomPoint> generateUpperSemicircle(CustomPoint centerPoint, int radius) {
        List<CustomPoint> firstQuadrantPoints = generateFirstQuadrant(centerPoint, radius);

        int shiftSize = 1;
        int lastPointIndex = firstQuadrantPoints.size() - 1;

        List<CustomPoint> secondQuadrantPoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint firstQuadrantPoint = firstQuadrantPoints.get(pointIndex);
            int x = (int) firstQuadrantPoint.getX();
            int y = (int) firstQuadrantPoint.getY();
            secondQuadrantPoints.add(new CustomPoint(x - shiftSize, y, 0, 0, Color.BLACK));

            CustomPoint nextFirstQuadrantPoint = firstQuadrantPoints.get(pointIndex + 1);
            int nextX = (int) nextFirstQuadrantPoint.getX();
            int nextY= (int) nextFirstQuadrantPoint.getY();
            shiftSize += Math.abs(nextX - x);
        }

        CustomPoint firstQuadrantPoint = firstQuadrantPoints.get(lastPointIndex);
        int x = (int) firstQuadrantPoint.getX();
        int y = (int) firstQuadrantPoint.getY();
        secondQuadrantPoints.add(new CustomPoint(x - shiftSize, y, 0, 0, Color.BLACK));

        List<CustomPoint> upperSemicirclePoints = new ArrayList<>();
        upperSemicirclePoints.addAll(secondQuadrantPoints);
        upperSemicirclePoints.addAll(firstQuadrantPoints);

        return upperSemicirclePoints;
    }

    private List<CustomPoint> generateFirstQuadrant(CustomPoint centerPoint, int radius) {
        List<CustomPoint> firstOctantPoints = generateFirstOctant(centerPoint, radius);

        int shiftSize = 1;
        int lastPointIndex = firstOctantPoints.size() - 1;

        List<CustomPoint> secondOctantPoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint firstOctantPoint = firstOctantPoints.get(pointIndex);
            int x = (int) firstOctantPoint.getX();
            int y = (int) firstOctantPoint.getY();
            secondOctantPoints.add(new CustomPoint(x - shiftSize, y + shiftSize, 0, 0, Color.BLACK));

            CustomPoint nextFirstOctantPoint = firstOctantPoints.get(pointIndex + 1);
            int nextX = (int) nextFirstOctantPoint.getX();
            int nextY= (int) nextFirstOctantPoint.getY();
            shiftSize += Math.abs(nextX - x) + Math.abs(nextY - y);
        }

        CustomPoint firstOctantPoint = firstOctantPoints.get(lastPointIndex);
        int x = (int) firstOctantPoint.getX();
        int y = (int) firstOctantPoint.getY();
        secondOctantPoints.add(new CustomPoint(x - shiftSize, y + shiftSize, 0, 0, Color.BLACK));

        List<CustomPoint> firstQuadrantPoints = new ArrayList<>();
        firstQuadrantPoints.addAll(secondOctantPoints);
        firstQuadrantPoints.addAll(firstOctantPoints);

        return firstQuadrantPoints;
    }

    private List<CustomPoint> generateFirstOctant(CustomPoint centerPoint, int radius) {
        List<CustomPoint> points = new ArrayList<>();

        int x = (int) centerPoint.getX();
        int y = (int) centerPoint.getY() + radius;
        int e = 2 - 2 * radius;

        points.add(new CustomPoint(x, e, 0, 0, Color.BLACK));

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
            } else if (e == 0) {
                x++;
                y--;
                e = e + 2 * x - 2 * y + 2;
            } else {
                int delta = 2 * e + 2* y - 1;
                if (delta <= 0) {
                    x++;
                    e = e + 2 * x + 1;
                } else {
                    x++;
                    y--;
                    e = e + 2 * x - 2 * y + 2;
                }
            }

            points.add(new CustomPoint(x, y, 0, 0, Color.BLACK));
        }

        return points;
    }
}
