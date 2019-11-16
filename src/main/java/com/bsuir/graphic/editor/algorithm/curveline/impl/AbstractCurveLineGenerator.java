package com.bsuir.graphic.editor.algorithm.curveline.impl;

import com.bsuir.graphic.editor.algorithm.curveline.CurveLineGenerator;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;
import com.bsuir.graphic.editor.util.calculation.matrix.MatrixCalculator;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCurveLineGenerator implements CurveLineGenerator {
    private static final int COORDINATES_NUM = 2;

    private MatrixCalculator calculator = new MatrixCalculator();

    @Override
    public List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step) {
        double[][] vectorArr = new double[points.size()][COORDINATES_NUM];
        for (int i = 0; i < points.size(); i++) {
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
            CustomPoint point = CustomPoint.simplePoint(P.el(0, 0), P.el(0, 1), Color.BLACK);
            resultPoints.add(point);
        }

        return resultPoints;
    }
}
