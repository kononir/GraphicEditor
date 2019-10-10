package com.bsuir.graphic.editor.algorithm.secorderlines;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.algorithm.DebugControllerException;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.CircleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.generator.impl.EllipseGenerator;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.CircleSpecification;
import com.bsuir.graphic.editor.algorithm.secorderlines.specification.EllipseSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.point.PointsCalculator;

import java.util.List;
import java.util.ListIterator;

public class FigureDebugController {
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private ListIterator<CustomPoint> debugIterator;

    public void controlStartingDebug(List<CustomPoint> points, AlgorithmType type) {
        PointsCalculator calculator = new PointsCalculator();

        switch (type) {
            case CIRCLE_GENERATION_ALGORITHM:
                int radius = calculator.calculateDistanceBetweenPoints(points.get(FIRST), points.get(SECOND));
                CircleSpecification circleSpecification = new CircleSpecification(points.get(FIRST), radius);
                debugIterator = new CircleGenerator().generateDebug(circleSpecification);
                break;
            case ELLIPSE_GENERATION_ALGORITHM:
                int a = calculator.calculateDeltaX(points.get(FIRST), points.get(SECOND));
                int b = calculator.calculateDeltaY(points.get(FIRST), points.get(SECOND));
                EllipseSpecification ellipseSpecification = new EllipseSpecification(points.get(FIRST), a, b);
                debugIterator = new EllipseGenerator().generateDebug(ellipseSpecification);
                break;
            case FIGURE_GENERATION_ALGORITHM:
                break;
        }
    }

    public CustomPoint controlNextStep() throws DebugControllerException {
        if (debugIterator.hasNext()) {
            return debugIterator.next();
        } else {
            throw new DebugControllerException("Reached last step!");
        }
    }

    public CustomPoint controlPrevStep() throws DebugControllerException {
        if (debugIterator.hasNext()) {
            return debugIterator.previous();
        } else {
            throw new DebugControllerException("Reached first step!");
        }
    }
}
