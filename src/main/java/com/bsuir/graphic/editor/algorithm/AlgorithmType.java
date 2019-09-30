package com.bsuir.graphic.editor.algorithm;

public enum AlgorithmType {
    DDA("DDA", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),
    BRESENHAM_ALGORITHM("Bresenham algorithm", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),
    WU_ALGORITHM("Wu algorithm", AlgorithmGroup.LINE_SEGMENT_ALGORITHMS),

    CIRCLE_GENERATION_ALGORITHM("Circle algorithm", AlgorithmGroup.SECOND_ORDER_LINES_ALGORITHMS),
    ELLIPSE_GENERATION_ALGORITHM("Ellipse algorithm", AlgorithmGroup.SECOND_ORDER_LINES_ALGORITHMS);

    private String name;
    private AlgorithmGroup group;

    AlgorithmType(String name, AlgorithmGroup group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public AlgorithmGroup getGroup() {
        return group;
    }
}