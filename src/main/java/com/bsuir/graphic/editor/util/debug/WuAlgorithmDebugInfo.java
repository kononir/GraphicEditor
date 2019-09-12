package com.bsuir.graphic.editor.util.debug;

import com.bsuir.graphic.editor.model.CustomPoint;

public class WuAlgorithmDebugInfo {
    private int step;
    private CustomPoint point1;
    private CustomPoint point2;
    private double error;
    private double adjustedError;

    public WuAlgorithmDebugInfo(int step, CustomPoint point1, CustomPoint point2, double error, double adjustedError) {
        this.step = step;
        this.point1 = point1;
        this.point2 = point2;
        this.error = error;
        this.adjustedError = adjustedError;
    }

    public int getStep() {
        return step;
    }

    public CustomPoint getPoint1() {
        return point1;
    }

    public CustomPoint getPoint2() {
        return point2;
    }

    public double getError() {
        return error;
    }

    public double getAdjustedError() {
        return adjustedError;
    }
}
