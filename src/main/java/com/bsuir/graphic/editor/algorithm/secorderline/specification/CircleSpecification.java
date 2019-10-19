package com.bsuir.graphic.editor.algorithm.secorderline.specification;

import com.bsuir.graphic.editor.model.CustomPoint;

public class CircleSpecification {
    private CustomPoint centerPoint;
    private int radius;

    public CircleSpecification(CustomPoint centerPoint, int radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public CustomPoint getCenterPoint() {
        return centerPoint;
    }

    public int getRadius() {
        return radius;
    }
}
