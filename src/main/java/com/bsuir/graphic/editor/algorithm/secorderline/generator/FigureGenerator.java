package com.bsuir.graphic.editor.algorithm.secorderline.generator;

import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;
import java.util.ListIterator;

public interface FigureGenerator<T> {
    List<CustomPoint> generate(T specification);

    ListIterator<CustomPoint> generateDebug(T specification);
}
