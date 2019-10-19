package com.bsuir.graphic.editor.util.calculation.matrix;

public class Matrix {
    private double[][] arr;
    private int width;
    private int height;

    public Matrix(double[][] arr) {
        this.arr = arr;
        this.width = arr[0].length;
        this.height = arr.length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double el(int i, int j) {
        return arr[i][j];
    }
}
