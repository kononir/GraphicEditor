package com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant;

import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;

public interface BQuadrant {
    CustomPoint generateNextPoint();
    BresenhamAlgorithmDebugInfo generateNextPointDebug(int step);
    CustomPoint rollbackToPrevPointDebug();
}
