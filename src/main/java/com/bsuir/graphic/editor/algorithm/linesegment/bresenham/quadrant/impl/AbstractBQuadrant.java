package com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant.impl;

import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.quadrant.BQuadrant;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractBQuadrant implements BQuadrant {
    protected int x;
    protected int y;
    protected int z;
    protected int t;
    protected int e;
    protected int maxProjection;
    protected int minProjection;

    private Deque<BresenhamAlgorithmDebugInfo> debugInfoDeque = new ArrayDeque<>();

    public AbstractBQuadrant(int x, int y, int z, int t, int e, int maxProjection, int minProjection) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.maxProjection = maxProjection;
        this.minProjection = minProjection;
    }

    @Override
    public abstract CustomPoint generateNextPoint();

    @Override
    public BresenhamAlgorithmDebugInfo generateNextPointDebug(int step) {
        int currE = e;
        CustomPoint point = generateNextPoint();

        BresenhamAlgorithmDebugInfo info = new BresenhamAlgorithmDebugInfo(step, point, currE, e);
        debugInfoDeque.add(info);
        return info;
    }

    @Override
    public CustomPoint rollbackToPrevPointDebug() {
        BresenhamAlgorithmDebugInfo info = debugInfoDeque.removeLast();
        CustomPoint point = info.getPoint();
        x = (int) point.getX();
        y = (int) point.getY();
        e = info.getError();

        return point;
    }
}
