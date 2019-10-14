package com.bsuir.graphic.editor.algorithm.secorderlines.specification;

import com.bsuir.graphic.editor.model.CustomPoint;

public class HyperboleSpecification {
    private CustomPoint centerPoint;
    private int a;
    private int b;

    public HyperboleSpecification(CustomPoint centerPoint, int a, int b) {
        this.centerPoint = centerPoint;
        this.a = a;
        this.b = b;
    }

    public CustomPoint getCenterPoint() {
        return centerPoint;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
