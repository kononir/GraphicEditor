package com.bsuir.graphic.editor.util.debug;

import com.bsuir.graphic.editor.model.CustomPoint;

public class DdaAlgorithmDebugInfo {
    private int step;
    private CustomPoint point;

    public DdaAlgorithmDebugInfo(int step, CustomPoint point) {
        this.step = step;
        this.point = point;
    }

    public int getStep() {
        return step;
    }

    public CustomPoint getPoint() {
        return point;
    }
}
