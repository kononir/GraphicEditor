package com.bsuir.graphic.editor.util.calculation.matrix;

public class MatrixCalculator {
    public Matrix mult(Matrix a, Matrix b) {
        if (a.getWidth() != b.getHeight()) {
            throw new MatrixCalculationsException("Matrix sizes don't match");
        }

        double[][] arr = new double[a.getHeight()][b.getWidth()];
        for (int i = 0; i < a.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                arr[i][j] = a.el(i, j) * b.el(i, j);
            }
        }

        Matrix c = new Matrix(arr);
        return c;
    }
}
