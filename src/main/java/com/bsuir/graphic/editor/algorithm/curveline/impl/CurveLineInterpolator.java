package com.bsuir.graphic.editor.algorithm.curveline.impl;

import com.bsuir.graphic.editor.algorithm.curveline.CurveLineGenerationException;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;

import java.util.List;

public class CurveLineInterpolator extends AbstractCurveLineGenerator {
    private static final int REQUIRED_POINTS_NUM = 4;

    @Override
    public List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step) {
        if (points.size() != REQUIRED_POINTS_NUM) {
            throw new CurveLineGenerationException("Wrong number of points (should be 4, "
                    + "but found " + points.size() + ")");
        }

        return super.generate(points, algorithmMatrix, step);
    }
}
