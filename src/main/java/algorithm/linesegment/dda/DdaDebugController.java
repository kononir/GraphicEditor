package algorithm.linesegment.dda;

import model.CustomPoint;
import util.debug.DdaAlgorithmDebugInfo;

public class DdaDebugController {
    private DdaAlgorithmPointGenerator generator = new DdaAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public DdaAlgorithmDebugInfo controlNextStep() {
        return generator.generateNextPointDebug();
    }

    public CustomPoint controlPrevStep() {
        return generator.rollbackToPrevPointDebug();
    }
}
