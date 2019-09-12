package algorithm.linesegment.dda;

import algorithm.linesegment.DebugControllerException;
import model.CustomPoint;
import util.debug.DdaAlgorithmDebugInfo;

public class DdaDebugController {
    private DdaAlgorithmPointGenerator generator = new DdaAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public DdaAlgorithmDebugInfo controlNextStep() throws DebugControllerException {
        DdaAlgorithmDebugInfo info = generator.generateNextPointDebug();
        if (info == null) {
            throw new DebugControllerException("Reached last step!");
        }

        return info;
    }

    public CustomPoint controlPrevStep() throws DebugControllerException {
        CustomPoint point = generator.rollbackToPrevPointDebug();
        if (point == null) {
            throw new DebugControllerException("Reached first step!");
        }

        return point;
    }
}
