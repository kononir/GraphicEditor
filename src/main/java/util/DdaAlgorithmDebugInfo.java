package util;

import model.CustomPoint;

public class DdaAlgorithmDebugInfo {
    private int stepNumber;
    private CustomPoint point;

    public DdaAlgorithmDebugInfo(int stepNumber, CustomPoint point) {
        this.stepNumber = stepNumber;
        this.point = point;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public CustomPoint getPoint() {
        return point;
    }

    public double getPointX() {
        return point.getX();
    }

    public double getPointY() {
        return point.getY();
    }
}
