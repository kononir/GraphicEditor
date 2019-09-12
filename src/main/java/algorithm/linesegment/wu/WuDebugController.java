package algorithm.linesegment.wu;

import algorithm.linesegment.DebugControllerException;
import model.CustomPoint;
import util.debug.WuAlgorithmDebugInfo;

import java.util.List;

public class WuDebugController {
    private WuAlgorithmPointGenerator generator = new WuAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public WuAlgorithmDebugInfo controlNextStep() throws DebugControllerException {
        WuAlgorithmDebugInfo info = generator.generateNextPointDebug();
        if (info == null) {
            throw new DebugControllerException("Reached last step!");
        }

        return info;
    }

    public List<CustomPoint> controlPrevStep() throws DebugControllerException {
        List<CustomPoint> points = generator.rollbackToPrevPointDebug();
        if (points == null) {
            throw new DebugControllerException("Reached first step!");
        }

        return points;
    }
}
