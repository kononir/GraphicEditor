package algorithm.linesegment.wu.quadrant.impl;

import algorithm.linesegment.wu.quadrant.WuQuadrant;
import model.CustomPoint;
import util.debug.WuAlgorithmDebugInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractWuQuadrant implements WuQuadrant {
    protected int x;
    protected int y;
    protected int z;
    protected int t;
    protected double e;
    protected double tangentAngle;

    private Queue<WuAlgorithmDebugInfo> debugInfoQueue = new LinkedList<>();

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
        debugInfoQueue.add(info);

        return info;
    }

    @Override
    public List<CustomPoint> rollbackToPrevPointsDebug() {
        WuAlgorithmDebugInfo info = debugInfoQueue.remove();
        CustomPoint point1 = info.getPoint1();
        CustomPoint point2 = info.getPoint2();

        x = (int) point1.getX();
        y = (int) point1.getY();
        e = info.getError();

        return Arrays.asList(point1, point2);
    }
}
