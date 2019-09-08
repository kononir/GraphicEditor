package algorithm.linesegment;

import model.CustomPoint;

import java.util.List;

public interface LineSegmentAlgorithm {
    List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint);
}
