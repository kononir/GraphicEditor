package util.debug;

import model.CustomPoint;

public class BresenhamAlgorithmDebugInfo {
    private int step;
    private CustomPoint point;
    private int error;
    private int adjustedError;

    public BresenhamAlgorithmDebugInfo(int step, CustomPoint point, int error, int adjustedError) {
        this.step = step;
        this.point = point;
        this.error = error;
        this.adjustedError = adjustedError;
    }

    public int getStep() {
        return step;
    }

    public CustomPoint getPoint() {
        return point;
    }

    public int getError() {
        return error;
    }

    public int getAdjustedError() {
        return adjustedError;
    }
}
