package com.bsuir.graphic.editor.controler;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.algorithm.curveline.CurveLineGenerator;
import com.bsuir.graphic.editor.algorithm.curveline.CurveLineMatrix;
import com.bsuir.graphic.editor.algorithm.curveline.impl.CurveLineExtrapolator;
import com.bsuir.graphic.editor.algorithm.curveline.impl.CurveLineInterpolator;
import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithm;
import com.bsuir.graphic.editor.algorithm.linesegment.LineSegmentAlgorithmFactory;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.FigureGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.CircleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.EllipseGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.HyperboleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.CircleSpecification;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.EllipseSpecification;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.HyperboleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.PointsCalculator;

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
                                                              CustomPoint radiusPoint, int limit) {
        int a = calculator.calculateDeltaX(centerPoint, radiusPoint);
        int b = calculator.calculateDeltaY(centerPoint, radiusPoint);
        HyperboleSpecification specification = new HyperboleSpecification(centerPoint, a, b, limit);

        FigureGenerator<HyperboleSpecification> generator = new HyperboleGenerator();
        return generator.generate(specification);
    }

    public List<CustomPoint> controlGeneratingCubeSpline(List<CustomPoint> points) {
        CurveLineGenerator generator = new CurveLineInterpolator();
        return generator.generate(points, CurveLineMatrix.HERMITE_MATRIX.getValue(), 0.01);
    }

    public List<CustomPoint> controlGeneratingBezierShape(List<CustomPoint> points) {
        CurveLineGenerator generator = new CurveLineInterpolator();
        return generator.generate(points, CurveLineMatrix.BEZIER_MATRIX.getValue(), 0.01);
    }

    public List<CustomPoint> controlGeneratingBSpline(List<CustomPoint> points) {
        CurveLineGenerator generator = new CurveLineExtrapolator();
        return generator.generate(points, CurveLineMatrix.B_MATRIX.getValue(), 0.01);
    }
}
