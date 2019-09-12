package com.bsuir.graphic.editor.algorithm.linesegment;

import com.bsuir.graphic.editor.algorithm.AlgorithmFactoryException;
import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.BresenhamAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.dda.DdaAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.wu.WuAlgorithm;

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
