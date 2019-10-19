package com.bsuir.graphic.editor.algorithm.curveline;

import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.calculation.matrix.Matrix;

import java.util.List;

public interface CurveLineGenerator {
    List<CustomPoint> generate(List<CustomPoint> points, Matrix algorithmMatrix, double step);
}
