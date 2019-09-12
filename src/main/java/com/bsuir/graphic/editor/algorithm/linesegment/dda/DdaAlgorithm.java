package com.bsuir.graphic.editor.algorithm.linesegment.dda;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class DdaAlgorithm implements LineSegmentAlgorithm {
    @Override
    public List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        DdaAlgorithmPointGenerator pointGenerator = new DdaAlgorithmPointGenerator();
        pointGenerator.setUp(startingPoint, endingPoint);

        List<CustomPoint> points = new ArrayList<>();
        CustomPoint currPoint = pointGenerator.generateNextPoint();
        while (currPoint != null) {
            points.add(currPoint);
            currPoint = pointGenerator.generateNextPoint();
        }

        return points;
    }
}
