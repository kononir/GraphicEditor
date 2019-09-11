package algorithm.linesegment.bresenham.quadrant.impl;

import algorithm.linesegment.bresenham.quadrant.BQuadrant;
import model.CustomPoint;
import util.debug.BresenhamAlgorithmDebugInfo;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractBQuadrant implements BQuadrant {
    protected int x;
    protected int y;
    protected int z;
    protected int t;
    protected int e;
    protected int maxProjection;
    protected int minProjection;

    private Queue<BresenhamAlgorithmDebugInfo> debugInfoQueue = new LinkedList<>();

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
        debugInfoQueue.add(info);
        return info;
    }

    @Override
    public CustomPoint rollbackToPrevPointDebug() {
        BresenhamAlgorithmDebugInfo info = debugInfoQueue.remove();
        CustomPoint point = info.getPoint();
        x = (int) point.getX();
        y = (int) point.getY();
        e = info.getError();

        return point;
    }
}
