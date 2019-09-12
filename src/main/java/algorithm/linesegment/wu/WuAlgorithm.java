package algorithm.linesegment.wu;

import algorithm.linesegment.LineSegmentAlgorithm;
import javafx.scene.paint.Color;
import model.CustomPoint;
import algorithm.linesegment.wu.quadrant.WuQuadrant;
import algorithm.linesegment.wu.quadrant.WuQuadrantFactory;

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
