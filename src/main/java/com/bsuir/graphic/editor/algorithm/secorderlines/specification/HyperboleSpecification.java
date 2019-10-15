package com.bsuir.graphic.editor.algorithm.secorderlines.specification;

import com.bsuir.graphic.editor.model.CustomPoint;

public class HyperboleSpecification {
    private CustomPoint centerPoint;
    private int a;
    private int b;
    private int limit;

    public HyperboleSpecification(CustomPoint centerPoint, int a, int b, int limit) {
        this.centerPoint = centerPoint;
        this.a = a;
        this.b = b;
        this.limit = limit;
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

    public int getLimit() {
        return limit;
    }
}
