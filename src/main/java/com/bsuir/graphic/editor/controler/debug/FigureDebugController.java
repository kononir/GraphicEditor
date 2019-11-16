package com.bsuir.graphic.editor.controler.debug;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.CircleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.EllipseGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.generator.impl.HyperboleGenerator;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.CircleSpecification;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.EllipseSpecification;
import com.bsuir.graphic.editor.algorithm.secorderline.specification.HyperboleSpecification;
import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;
import java.util.ListIterator;

public class FigureDebugController {
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private ListIterator<CustomPoint> debugIterator;

    public void controlStartingDebug(CustomPoint center, List<Integer> radius, AlgorithmType type) {
        switch (type) {
            case CIRCLE_GENERATION_ALGORITHM:
                CircleSpecification circleSpecification = new CircleSpecification(center, radius.get(FIRST));
                debugIterator = new CircleGenerator().generateDebug(circleSpecification);
                break;
            case ELLIPSE_GENERATION_ALGORITHM:
                EllipseSpecification ellipseSpecification
                        = new EllipseSpecification(center, radius.get(FIRST), radius.get(SECOND));
                debugIterator = new EllipseGenerator().generateDebug(ellipseSpecification);
                break;
            case HYPERBOLE_GENERATION_ALGORITHM:
                HyperboleSpecification hyperboleSpecification
                        = new HyperboleSpecification(center, radius.get(FIRST), radius.get(SECOND), 650 / 10);
                debugIterator = new HyperboleGenerator().generateDebug(hyperboleSpecification);
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
        if (debugIterator.hasPrevious()) {
            return debugIterator.previous();
        } else {
            throw new DebugControllerException("Reached first step!");
        }
    }
}
