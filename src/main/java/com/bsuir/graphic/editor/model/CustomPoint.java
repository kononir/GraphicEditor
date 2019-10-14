package com.bsuir.graphic.editor.model;

import javafx.scene.paint.Color;

public class CustomPoint {
    private double x;
    private double y;
    private double z;
    private double t;

    private Color color;

    public CustomPoint(double x, double y, double z, double t, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.color = color;
    }

    public static CustomPoint simplePoint(double x, double y, Color color) {
        return new CustomPoint(x, y, 0, 0, color);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getT() {
        return t;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return 13 * (int) x + (int) y + (int) z + (int) t + color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;

        CustomPoint p = (CustomPoint) obj;
        return x == p.x && y == p.y && z == p.z && t == p.t && color.equals(p.color);
    }

    @Override
    public String toString() {
        return "CustomPoint@ x = " + x + ";y = " + y + ";z = " + z + ";t = " + t
                + ";color = " + color.toString();
    }
}
