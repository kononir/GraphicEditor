package com.bsuir.graphic.editor.util.point;

import com.bsuir.graphic.editor.model.CustomPoint;

public class PointsCalculator {
    public int calculateDistanceBetweenPoints(CustomPoint point1, CustomPoint point2) {
        int deltaX = calculateDeltaX(point1, point2);
        int deltaY = calculateDeltaY(point1, point2);
        return (int) (Math.sqrt(deltaX * deltaX + deltaY * deltaY) + 0.5);
    }

    public int calculateDeltaX(CustomPoint point1, CustomPoint point2) {
        return (int) Math.abs(point2.getX() - point1.getX());
    }

    public int calculateDeltaY(CustomPoint point1, CustomPoint point2) {
        return (int) Math.abs(point2.getY() - point1.getY());
    }
}
