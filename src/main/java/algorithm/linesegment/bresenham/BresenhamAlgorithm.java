package algorithm.linesegment.bresenham;

import algorithm.linesegment.LineSegmentAlgorithm;
import model.CustomPoint;

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
