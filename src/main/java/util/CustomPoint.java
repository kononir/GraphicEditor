package util;

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
}
