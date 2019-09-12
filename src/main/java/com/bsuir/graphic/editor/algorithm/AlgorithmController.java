package com.bsuir.graphic.editor.algorithm;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithmFactory;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;

public class AlgorithmController {
    public List<CustomPoint> controlGeneratingLineSegmentPoints(AlgorithmType algorithmType, CustomPoint startingPoint,
                                                                CustomPoint endingPoint) {
        LineSegmentAlgorithmFactory factory = new LineSegmentAlgorithmFactory();
        LineSegmentAlgorithm algorithm = factory.create(algorithmType);

        return algorithm.generateLineSegment(startingPoint, endingPoint);
    }
}
