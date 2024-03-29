package com.bsuir.graphic.editor.algorithm;

public enum AlgorithmType {
    DDA("DDA", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),
    BRESENHAM_ALGORITHM("Bresenham algorithm", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),
    WU_ALGORITHM("Wu algorithm", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),

    CIRCLE_GENERATION_ALGORITHM("Circle", AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS),
    ELLIPSE_GENERATION_ALGORITHM("Ellipse", AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS),
    HYPERBOLE_GENERATION_ALGORITHM("Hyperbole", AlgorithmGroup.SEC_ORDER_LINE_ALGORITHMS),

    HERMIT_INTERPOLATION_ALGORITHM("Cube spline", AlgorithmGroup.CURVE_LINE_ALGORITHMS),
    BEZIER_INTERPOLATION_ALGORITHM("Bezier shape", AlgorithmGroup.CURVE_LINE_ALGORITHMS),
    B_SPLINE_EXTRAPOLATION_ALGORITHM("B-spline", AlgorithmGroup.CURVE_LINE_ALGORITHMS);

    private String name;
    private AlgorithmGroup group;

    AlgorithmType(String name, AlgorithmGroup group) {
        this.name = name;
        this.group = group;
    }

    public AlgorithmGroup getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return name;
    }
}
