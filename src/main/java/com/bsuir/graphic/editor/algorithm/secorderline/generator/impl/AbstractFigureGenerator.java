package com.bsuir.graphic.editor.algorithm.secorderline.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderline.generator.FigureGenerator;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.*;

public abstract class AbstractFigureGenerator<T> implements FigureGenerator<T> {

    protected List<CustomPoint> reflectOY(List<CustomPoint> points, CustomPoint center) {
        List<CustomPoint> reflectedPoints = new ArrayList<>();

        double xAxe = center.getX();
        for (CustomPoint point : points) {
            double x = point.getX();
            double reflectedX = xAxe + (xAxe - x);
            CustomPoint reflectedPoint = CustomPoint.simplePoint(reflectedX, point.getY(), point.getColor());
            reflectedPoints.add(reflectedPoint);
        }
        Collections.reverse(reflectedPoints);

        List<CustomPoint> allPoints = new ArrayList<>(reflectedPoints);
        allPoints.addAll(points);

        return allPoints;
    }

    protected List<CustomPoint> reflectOX(List<CustomPoint> points, CustomPoint center) {
        List<CustomPoint> reflectedPoints = new ArrayList<>();

        double yAxe = center.getY();
        for (CustomPoint point : points) {
            double y = point.getY();
            double reflectedY = yAxe + (yAxe - y);
            CustomPoint reflectedPoint = CustomPoint.simplePoint(point.getX(), reflectedY, point.getColor());
            reflectedPoints.add(reflectedPoint);
        }
        Collections.reverse(reflectedPoints);

        List<CustomPoint> allPoints = new ArrayList<>(reflectedPoints);
        allPoints.addAll(points);

        return allPoints;
    }

    @Override
    public ListIterator<CustomPoint> generateDebug(T specification) {
        Set<CustomPoint> distinctPoints = new LinkedHashSet<>(generate(specification));
        return new LinkedList<>(distinctPoints).listIterator();
    }
}
