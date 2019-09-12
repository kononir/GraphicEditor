package com.bsuir.graphic.editor.algorithm.linesegment.wu;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class WuAlgorithm implements LineSegmentAlgorithm {
    @Override
    public List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        WuAlgorithmPointGenerator pointGenerator = new WuAlgorithmPointGenerator();
        pointGenerator.setUp(startingPoint, endingPoint);

        List<CustomPoint> points = new ArrayList<>();
        List<CustomPoint> currPoints = pointGenerator.generateNextPoints();
        while (currPoints != null) {
            points.addAll(currPoints);
            currPoints = pointGenerator.generateNextPoints();
        }

        return points;
    }
}
