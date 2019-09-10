package algorithm.linesegment.dda;

import model.CustomPoint;
import util.DdaAlgorithmDebugInfo;

public class DdaDebugController {
    private DdaAlgorithmPointGenerator ddaGenerator = new DdaAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        ddaGenerator.setUp(startingPoint, endingPoint);
    }

    public DdaAlgorithmDebugInfo controlNextStep() {
        return ddaGenerator.generateNextPointDebug();
    }

    public CustomPoint controlPrevStep() {
        return ddaGenerator.rollbackToPrevPointDebug();
    }
}
