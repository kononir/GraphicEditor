package com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant;

import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;

import java.util.List;

public interface WuQuadrant {
    List<CustomPoint> generateNextPoints();
    WuAlgorithmDebugInfo generateNextPointsDebug(int step);
    List<CustomPoint> rollbackToPrevPointsDebug();
}
