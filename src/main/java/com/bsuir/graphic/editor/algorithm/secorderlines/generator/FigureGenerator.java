package com.bsuir.graphic.editor.algorithm.secorderlines.generator;

import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface FigureGenerator<T> {
    List<CustomPoint> generate(T specification);

    ListIterator<CustomPoint> generateDebug(T specification);
}
