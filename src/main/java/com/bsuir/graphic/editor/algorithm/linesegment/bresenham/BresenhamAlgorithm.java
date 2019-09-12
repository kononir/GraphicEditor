package com.bsuir.graphic.editor.algorithm.linesegment.bresenham;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class BresenhamAlgorithm implements LineSegmentAlgorithm {
    @Override
    public List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        BresenhamAlgorithmPointGenerator pointGenerator = new BresenhamAlgorithmPointGenerator();
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
