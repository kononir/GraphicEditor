package algorithm;

import algorithm.linesegment.LineSegmentAlgorithm;
import algorithm.linesegment.LineSegmentAlgorithmFactory;
import util.CustomPoint;

import java.util.List;

public class AlgorithmController {
    public List<CustomPoint> controlGeneratingLineSegmentPoints(AlgorithmType algorithmType, CustomPoint startingPoint,
                                                                CustomPoint endingPoint) {
        LineSegmentAlgorithmFactory factory = new LineSegmentAlgorithmFactory();
        LineSegmentAlgorithm algorithm = factory.create(algorithmType);

        return algorithm.generateLineSegment(startingPoint, endingPoint);
    }
}
