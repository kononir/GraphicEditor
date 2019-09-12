package com.bsuir.graphic.editor.algorithm.linesegment;

import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;

public interface LineSegmentAlgorithm {
    List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint);
}
