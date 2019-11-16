package com.bsuir.graphic.editor.algorithm.curveline.impl;

import com.bsuir.graphic.editor.algorithm.curveline.CurveLineGenerationException;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;
import com.bsuir.graphic.editor.util.calculation.matrix.MatrixCalculator;

import java.util.List;

public class CurveLineExtrapolator extends AbstractCurveLineGenerator {
    private static final int MIN_POINTS_NUM = 4;

    private MatrixCalculator calculator = new MatrixCalculator();

    @Override
    public List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step) {
        if (points.size() % MIN_POINTS_NUM != 0) {
            throw new CurveLineGenerationException("Wrong number of points (should be"
                    + "but found " + points.size() + ")");
        }

        return super.generate(points, algorithmMatrix, step);
    }
}
