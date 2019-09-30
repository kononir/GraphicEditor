package com.bsuir.graphic.editor.algorithm.linesegment.bresenham;

import com.bsuir.graphic.editor.algorithm.linesegment.DebugControllerException;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;

public class BDebugController {
    private BresenhamAlgorithmPointGenerator generator = new BresenhamAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public BresenhamAlgorithmDebugInfo controlNextStep() throws DebugControllerException {
        BresenhamAlgorithmDebugInfo info = generator.generateNextPointDebug();
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