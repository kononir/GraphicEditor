package com.bsuir.graphic.editor.algorithm.curveline.impl;

import com.bsuir.graphic.editor.algorithm.curveline.CurveLineGenerationException;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;
import com.bsuir.graphic.editor.util.calculation.matrix.MatrixCalculator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CurveLineExtrapolator extends AbstractCurveLineGenerator {
    private static final int MIN_POINTS_NUM = 4;
    private static final int WINDOW = 4;

    private MatrixCalculator calculator = new MatrixCalculator();

    private boolean isCircular;

    public CurveLineExtrapolator(boolean isCircular) {
        this.isCircular = isCircular;
    }

    @Override
    public List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step) {
        if (points.size() < MIN_POINTS_NUM) {
            throw new CurveLineGenerationException("Wrong number of points (should be more than "
                    + MIN_POINTS_NUM + "but found " + points.size() + ")");
        }

        int pointsNumber = points.size();

        Set<CustomPoint> uniquePoints = new LinkedHashSet<>();
        for (int i = 0; i < pointsNumber - 3; i++) {
            uniquePoints.addAll(super.generate(points.subList(i, i + WINDOW), algorithmMatrix, step));
        }

        if (isCircular) {
            for (int i = pointsNumber - 3, j = 0; i < pointsNumber; i++, j++) {
                List<CustomPoint> currFourPoints = new ArrayList<>();
                for (int i1 = i; i1 < pointsNumber; i1++) {
                    currFourPoints.add(points.get(i1));
                }
                for (int j1 = 0; j1 < j + 1; j1++) {
                    currFourPoints.add(points.get(j1));
                }

                uniquePoints.addAll(super.generate(currFourPoints, algorithmMatrix, step));
            }
        }

        return new ArrayList<>(uniquePoints);
    }
}
