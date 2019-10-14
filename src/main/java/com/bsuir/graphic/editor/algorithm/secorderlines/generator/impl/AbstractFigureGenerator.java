package com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl;

import com.bsuir.graphic.editor.algorithm.secorderlines.generator.FigureGenerator;
import com.bsuir.graphic.editor.model.CustomPoint;
import javafx.scene.paint.Color;

import java.util.*;

public abstract class AbstractFigureGenerator<T> implements FigureGenerator<T> {
    protected List<CustomPoint> reflectFirstOctantXY(List<CustomPoint> firstOctantPoints) {
        int shiftSize = 0;
        int lastPointIndex = firstOctantPoints.size() - 1;

        List<CustomPoint> secondOctantPoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint firstOctantPoint = firstOctantPoints.get(pointIndex);
            int x = (int) firstOctantPoint.getX();
            int y = (int) firstOctantPoint.getY();
            secondOctantPoints.add(new CustomPoint(x - shiftSize, y + shiftSize, 0, 0, Color.BLACK));

            CustomPoint nextFirstOctantPoint = firstOctantPoints.get(pointIndex + 1);
            int nextX = (int) nextFirstOctantPoint.getX();
            int nextY= (int) nextFirstOctantPoint.getY();
            shiftSize += Math.abs(nextX - x) + Math.abs(nextY - y);
        }

        CustomPoint firstOctantPoint = firstOctantPoints.get(lastPointIndex);
        int x = (int) firstOctantPoint.getX();
        int y = (int) firstOctantPoint.getY();
        secondOctantPoints.add(new CustomPoint(x - shiftSize, y + shiftSize, 0, 0, Color.BLACK));

        Collections.reverse(secondOctantPoints);

        List<CustomPoint> firstQuadrantPoints = new ArrayList<>();
        firstQuadrantPoints.addAll(secondOctantPoints);
        firstQuadrantPoints.addAll(firstOctantPoints);

        return firstQuadrantPoints;
    }

    protected List<CustomPoint> reflectFirstQuadrantOY(List<CustomPoint> firstQuadrantPoints) {
        int shiftSize = 0;
        int lastPointIndex = firstQuadrantPoints.size() - 1;

        List<CustomPoint> secondQuadrantPoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint firstQuadrantPoint = firstQuadrantPoints.get(pointIndex);
            int x = (int) firstQuadrantPoint.getX();
            int y = (int) firstQuadrantPoint.getY();
            secondQuadrantPoints.add(new CustomPoint(x - shiftSize, y, 0, 0, Color.BLACK));

            CustomPoint nextFirstQuadrantPoint = firstQuadrantPoints.get(pointIndex + 1);
            int nextX = (int) nextFirstQuadrantPoint.getX();
            int nextY= (int) nextFirstQuadrantPoint.getY();
            shiftSize += 2 * Math.abs(nextX - x);
        }

        CustomPoint firstQuadrantPoint = firstQuadrantPoints.get(lastPointIndex);
        int x = (int) firstQuadrantPoint.getX();
        int y = (int) firstQuadrantPoint.getY();
        secondQuadrantPoints.add(new CustomPoint(x - shiftSize, y, 0, 0, Color.BLACK));

        Collections.reverse(secondQuadrantPoints);

        List<CustomPoint> upperFigurePartPoints = new ArrayList<>();
        upperFigurePartPoints.addAll(secondQuadrantPoints);
        upperFigurePartPoints.addAll(firstQuadrantPoints);

        return upperFigurePartPoints;
    }

    protected List<CustomPoint> reflectUpperFigurePartOX(List<CustomPoint> upperFigurePartPoints) {
        int shiftSize = 0;
        int lastPointIndex = upperFigurePartPoints.size() - 1;

        List<CustomPoint> lowerSemicirclePoints = new ArrayList<>();
        for (int pointIndex = 0; pointIndex < lastPointIndex; pointIndex++) {
            CustomPoint upperSemicirclePoint = upperFigurePartPoints.get(pointIndex);
            int x = (int) upperSemicirclePoint.getX();
            int y = (int) upperSemicirclePoint.getY();
            lowerSemicirclePoints.add(new CustomPoint(x, y - shiftSize, 0, 0, Color.BLACK));

            CustomPoint nextUpperSemicirclePoint = upperFigurePartPoints.get(pointIndex + 1);
            int nextX = (int) nextUpperSemicirclePoint.getX();
            int nextY= (int) nextUpperSemicirclePoint.getY();
            shiftSize += 2 * (nextY - y);
        }

        CustomPoint upperSemicirclePoint = upperFigurePartPoints.get(lastPointIndex);
        int x = (int) upperSemicirclePoint.getX();
        int y = (int) upperSemicirclePoint.getY();
        lowerSemicirclePoints.add(new CustomPoint(x, y - shiftSize, 0, 0, Color.BLACK));

        Collections.reverse(lowerSemicirclePoints);

        List<CustomPoint> figurePoints = new ArrayList<>();
        figurePoints.addAll(upperFigurePartPoints);
        figurePoints.addAll(lowerSemicirclePoints);

        return figurePoints;
    }

    @Override
    public ListIterator<CustomPoint> generateDebug(T specification) {
        return new LinkedList<>(generate(specification)).listIterator();
    }
}
