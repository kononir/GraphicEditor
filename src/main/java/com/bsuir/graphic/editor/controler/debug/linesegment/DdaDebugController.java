package com.bsuir.graphic.editor.controler.debug.linesegment;

import com.bsuir.graphic.editor.controler.debug.DebugControllerException;
import com.bsuir.graphic.editor.algorithm.linesegment.dda.DdaAlgorithmPointGenerator;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.DdaAlgorithmDebugInfo;

public class DdaDebugController {
    private DdaAlgorithmPointGenerator generator = new DdaAlgorithmPointGenerator();

    public void controlStartingDebug(CustomPoint startingPoint, CustomPoint endingPoint) {
        generator.setUp(startingPoint, endingPoint);
    }

    public DdaAlgorithmDebugInfo controlNextStep() throws DebugControllerException {
        DdaAlgorithmDebugInfo info = generator.generateNextPointDebug();
        if (info.getPoint() == null) {
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
