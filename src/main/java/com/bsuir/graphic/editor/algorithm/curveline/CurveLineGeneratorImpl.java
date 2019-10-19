package com.bsuir.graphic.editor.algorithm.curveline;

import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;
import com.bsuir.graphic.editor.util.calculation.matrix.MatrixCalculator;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CurveLineGeneratorImpl implements CurveLineGenerator {
    private static final int REQUIRED_POINTS_NUM = 4;
    private static final int COORDINATES_NUM = 2;

    private MatrixCalculator calculator;

    @Override
    public List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step) {
        if (points.size() != REQUIRED_POINTS_NUM) {
            throw new CurveLineGenerationException("Wrong number of points (should be 4, "
                    + "but found " + points.size() + ")");
        }

        double[][] vectorArr = new double[REQUIRED_POINTS_NUM][COORDINATES_NUM];
        for (int i = 0; i < REQUIRED_POINTS_NUM; i++) {
            CustomPoint point = points.get(i);
            vectorArr[i][0] = point.getX();
            vectorArr[i][1] = point.getY();
        }
        Matrix vector = new Matrix(vectorArr);

        Matrix Cx = calculator.mult(algorithmMatrix, vector);

        List<CustomPoint> resultPoints = new ArrayList<>();
        for (double t = 0; t <= 1.0; t += step) {
            double t2 = t * t;
            double[][] arr = {{t2 * t, t2, t, 1.0}};
            Matrix T = new Matrix(arr);

            Matrix P = calculator.mult(T, Cx);
            for (int i = 0; i < REQUIRED_POINTS_NUM; i++) {
                CustomPoint point = CustomPoint.simplePoint(P.el(i, 0), P.el(i, 1), Color.BLACK);
                resultPoints.add(point);
            }
        }

        return resultPoints;
    }
}
