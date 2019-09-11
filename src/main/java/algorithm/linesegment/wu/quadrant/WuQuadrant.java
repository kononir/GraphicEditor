package algorithm.linesegment.wu.quadrant;

import model.CustomPoint;
import util.debug.WuAlgorithmDebugInfo;

import java.util.List;

public interface WuQuadrant {
    List<CustomPoint> generateNextPoints();
    WuAlgorithmDebugInfo generateNextPointsDebug(int step);
    List<CustomPoint> rollbackToPrevPointsDebug();
}
