package com.bsuir.graphic.editor.algorithm;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithmFactory;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.FigureGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.CircleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.EllipseGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.CircleSpecification;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.EllipseSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;

public class AlgorithmController {
    public List<CustomPoint> controlGeneratingLineSegmentPoints(
            AlgorithmType algorithmType, CustomPoint startingPoint, CustomPoint endingPoint) {
        LineSegmentAlgorithmFactory factory = new LineSegmentAlgorithmFactory();
        LineSegmentAlgorithm algorithm = factory.create(algorithmType);

        return algorithm.generateLineSegment(startingPoint, endingPoint);
    }

    public List<CustomPoint> controlGeneratingCirclePoints(CustomPoint centerPoint,
                                                           CustomPoint radiusPoint) {
        int radius = calculateDistanceBetweenPoints(centerPoint, radiusPoint);
        CircleSpecification specification = new CircleSpecification(centerPoint, radius);

        FigureGenerator<CircleSpecification> generator = new CircleGenerator();
        return generator.generate(specification);
    }

    public List<CustomPoint> controlGeneratingEllipsePoints(CustomPoint centerPoint,
                                                            CustomPoint aPoint,
                                                            CustomPoint bPoint) {
        int a = calculateDistanceBetweenPoints(centerPoint, aPoint);
        int b = calculateDistanceBetweenPoints(centerPoint, bPoint);
        EllipseSpecification specification = new EllipseSpecification(centerPoint, a, b);

        FigureGenerator<EllipseSpecification> generator = new EllipseGenerator();
        return generator.generate(specification);
    }

    private int calculateDistanceBetweenPoints(CustomPoint point1, CustomPoint point2) {
        double deltaX = Math.abs(point2.getX() - point1.getX());
        double deltaY = Math.abs(point2.getY() - point1.getY());
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
