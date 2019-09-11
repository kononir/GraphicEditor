package algorithm.linesegment.bresenham.quadrant;

import model.CustomPoint;
import util.debug.BresenhamAlgorithmDebugInfo;

public interface BQuadrant {
    CustomPoint generateNextPoint();
    BresenhamAlgorithmDebugInfo generateNextPointDebug(int step);
    CustomPoint rollbackToPrevPointDebug();
}
