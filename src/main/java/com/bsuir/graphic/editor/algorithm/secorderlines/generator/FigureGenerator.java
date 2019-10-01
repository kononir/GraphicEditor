package com.bsuir.graphic.editor.algorithm.secorderlines.generator;

import com.bsuir.graphic.editor.model.CustomPoint;

import java.util.List;

public interface FigureGenerator<T> {
    List<CustomPoint> generate(T specification);
}