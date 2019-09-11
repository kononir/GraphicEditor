package util.debug;

import javafx.util.Pair;
import model.CustomPoint;

import java.util.List;

public class WuAlgorithmDebugInfo {
    private int step;
    private List<CustomPoint> points;
    private double error;
    private double adjustedError;

    public WuAlgorithmDebugInfo(int step, List<CustomPoint> points, double error, double adjustedError) {
        this.step = step;
        this.points = points;
        this.error = error;
        this.adjustedError = adjustedError;
    }

    public int getStep() {
        return step;
    }

    public List<CustomPoint> getPoints() {
        return points;
    }

    public double getError() {
        return error;
    }

    public double getAdjustedError() {
        return adjustedError;
    }
}
