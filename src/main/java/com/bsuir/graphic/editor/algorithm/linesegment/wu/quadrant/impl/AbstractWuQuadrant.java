package com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.impl;

import com.bsuir.graphic.editor.algorithm.linesegment.wu.quadrant.WuQuadrant;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;

import java.util.*;

public abstract class AbstractWuQuadrant implements WuQuadrant {
    protected int x;
    protected int y;
    protected int z;
    protected int t;
    protected double e;
    protected double tangentAngle;

    private Deque<WuAlgorithmDebugInfo> debugInfoDeque = new ArrayDeque<>();

    public AbstractWuQuadrant(int x, int y, int z, int t, double e, double tangentAngle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.e = e;
        this.tangentAngle = tangentAngle;
    }

    @Override
    public abstract List<CustomPoint> generateNextPoints();

    @Override
    public WuAlgorithmDebugInfo generateNextPointsDebug(int step) {
        double currE = e;
        List<CustomPoint> points = generateNextPoints();

        WuAlgorithmDebugInfo info = new WuAlgorithmDebugInfo(step, points.get(0), points.get(1), currE, e);
        debugInfoDeque.add(info);

        return info;
    }

    @Override
    public List<CustomPoint> rollbackToPrevPointsDebug() {
        WuAlgorithmDebugInfo info = debugInfoDeque.removeLast();
        CustomPoint point1 = info.getPoint1();
        CustomPoint point2 = info.getPoint2();

        x = (int) point1.getX();
        y = (int) point1.getY();
        e = info.getError();

        return Arrays.asList(point1, point2);
    }
}
