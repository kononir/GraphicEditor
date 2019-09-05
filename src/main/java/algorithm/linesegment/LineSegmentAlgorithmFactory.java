package algorithm.linesegment;

import algorithm.AlgorithmFactoryException;
import algorithm.AlgorithmType;
import algorithm.linesegment.impl.BresenhamAlgorithm;
import algorithm.linesegment.impl.DdaAlgorithm;
import algorithm.linesegment.impl.WuAlgorithm;

public class LineSegmentAlgorithmFactory {
    public LineSegmentAlgorithm create(AlgorithmType type) {
        LineSegmentAlgorithm algorithm;

        switch (type) {
            case DDA:
                algorithm = new DdaAlgorithm();
                break;
            case BRESENHAM_ALGORITHM:
                algorithm = new BresenhamAlgorithm();
                break;
            case WU_ALGORITHM:
                algorithm = new WuAlgorithm();
                break;
            default:
                throw new AlgorithmFactoryException("Wrong algorithm name");
        }

        return algorithm;
    }
}
