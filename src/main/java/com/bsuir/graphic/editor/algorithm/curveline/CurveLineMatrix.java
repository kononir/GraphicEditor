package com.bsuir.graphic.editor.algorithm.curveline;

import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;

public enum CurveLineMatrix {
    HERMITE_MATRIX(new Matrix(new double[][]{
            {2, -2, 1, 1},
            {-3, 3, -2, -1},
            {0, 0, 1, 0},
            {1, 0, 0, 0}
    })),
    BEZIER_MATRIX(new Matrix(new double[][]{
            {-1, 3, -3, 1},
            {3, -6, 3, 0},
            {-3, 3, 0, 0},
            {1, 0, 0, 0}
    })),
    B_MATRIX(new Matrix(new double[][]{
            {-1.0 / 6.0, 1.0 / 2.0, -1.0 / 2.0, 1.0 / 6.0},
            {1.0 / 2.0, -1.0, 1.0 / 2.0, 0},
            {-1.0 / 2.0, 0, 1.0 / 2.0, 0},
            {1.0 / 6.0, 2.0 / 3.0, 1.0 / 6.0, 0}
    }));

    private Matrix matrix;

    CurveLineMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix getValue() {
        return matrix;
    }
}
