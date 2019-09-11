package util.debug;

import model.CustomPoint;

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
