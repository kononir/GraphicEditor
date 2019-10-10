package com.bsuir.graphic.editor.algorithm.linesegment.wu;

import com.bsuir.graphic.editor.algorithm.DebugControllerException;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;

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
