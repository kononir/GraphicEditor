package com.bsuir.graphic.editor.algorithm;

import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithmFactory;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.FigureGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.CircleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.EllipseGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.HyperboleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.CircleSpecification;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.EllipseSpecification;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.HyperboleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.point.PointsCalculator;

import java.util.List;

public class AlgorithmController {
    private PointsCalculator calculator = new PointsCalculator();

    public List<CustomPoint> controlGeneratingLineSegmentPoints(
            AlgorithmType algorithmType, CustomPoint startingPoint, CustomPoint endingPoint) {
        LineSegmentAlgorithmFactory factory = new LineSegmentAlgorithmFactory();
        LineSegmentAlgorithm algorithm = factory.create(algorithmType);

        return algorithm.generateLineSegment(startingPoint, endingPoint);
    }

    public List<CustomPoint> controlGeneratingCirclePoints(CustomPoint centerPoint,
                                                           CustomPoint radiusPoint) {
        int radius = calculator.calculateDistanceBetweenPoints(centerPoint, radiusPoint);
        CircleSpecification specification = new CircleSpecification(centerPoint, radius);

        FigureGenerator<CircleSpecification> generator = new CircleGenerator();
        return generator.generate(specification);
    }

    public List<CustomPoint> controlGeneratingEllipsePoints(CustomPoint centerPoint,
                                                            CustomPoint radiusPoint) {
        int a = calculator.calculateDeltaX(centerPoint, radiusPoint);
        int b = calculator.calculateDeltaY(centerPoint, radiusPoint);
        EllipseSpecification specification = new EllipseSpecification(centerPoint, a, b);

        FigureGenerator<EllipseSpecification> generator = new EllipseGenerator();
        return generator.generate(specification);
    }

    public List<CustomPoint> controlGeneratingHyperbolePoints(CustomPoint centerPoint,
                                                              CustomPoint radiusPoint) {
        int a = calculator.calculateDeltaX(centerPoint, radiusPoint);
        int b = calculator.calculateDeltaY(centerPoint, radiusPoint);
        HyperboleSpecification specification = new HyperboleSpecification(centerPoint, a, b);

        FigureGenerator<HyperboleSpecification> generator = new HyperboleGenerator();
        return generator.generate(specification);
    }
}
