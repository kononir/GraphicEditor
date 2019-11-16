package com.bsuir.graphic.editor.algorithm;

public enum AlgorithmGroup {
    LINE_SEGMENT_ALGORITHMS("Line segment"),
    SEC_ORDER_LINE_ALGORITHMS("Second order lines"),
    CURVE_LINE_ALGORITHMS("Curve lines");

    private String name;

    AlgorithmGroup(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
