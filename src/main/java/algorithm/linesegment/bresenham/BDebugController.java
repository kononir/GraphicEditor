package algorithm.linesegment.bresenham;

import model.CustomPoint;
import util.debug.BresenhamAlgorithmDebugInfo;

public class BDebugController {
    private BresenhamAlgorithmPointGenerator generator = new BresenhamAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public BresenhamAlgorithmDebugInfo controlNextStep() {
        return generator.generateNextPointDebug();
    }

    public CustomPoint controlPrevStep() {
        return generator.rollbackToPrevPointDebug();
    }
}
