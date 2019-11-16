package com.bsuir.graphic.editor.util.calculation.matrix;

public class MatrixCalculator {
    public Matrix mult(Matrix a, Matrix b) {
        if (a.getWidth() != b.getHeight()) {
            throw new MatrixCalculationsException("Matrix sizes don't match");
        }

        int multNum = a.getWidth();
        int height = a.getHeight();
        int width = b.getWidth();

        double[][] arr = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < multNum; k++) {
                    arr[i][j] += a.el(i, k) * b.el(k, j);
                }
            }
        }

        Matrix c = new Matrix(arr);
        return c;
    }
}
