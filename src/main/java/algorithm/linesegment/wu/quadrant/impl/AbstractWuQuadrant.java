package algorithm.linesegment.wu.quadrant.impl;

import algorithm.linesegment.wu.quadrant.WuQuadrant;
import model.CustomPoint;
import util.debug.WuAlgorithmDebugInfo;

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

        WuAlgorithmDebugInfo info = new WuAlgorithmDebugInfo(step, points, currE, e);
        debugInfoQueue.add(info);

        return info;
    }

    @Override
    public List<CustomPoint> rollbackToPrevPointsDebug() {
        WuAlgorithmDebugInfo info = debugInfoQueue.remove();
        List<CustomPoint> points = info.getPoints();
        CustomPoint point = points.get(0);
        x = (int) point.getX();
        y = (int) point.getY();
        e = info.getError();

        return points;
    }
}
