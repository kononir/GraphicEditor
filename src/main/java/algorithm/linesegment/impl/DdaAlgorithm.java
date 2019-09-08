package algorithm.linesegment.impl;

import algorithm.linesegment.LineSegmentAlgorithm;
import javafx.scene.paint.Color;
import model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class DdaAlgorithm implements LineSegmentAlgorithm {
    @Override
    public List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        double x1 = startingPoint.getX();
        double x2 = endingPoint.getX();
        double y1 = startingPoint.getY();
        double y2 = endingPoint.getY();
        double z = startingPoint.getZ();
        double t = startingPoint.getT();

        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        double projectionX = Math.abs(deltaX);
        double projectionY = Math.abs(deltaY);
        double maxProjection = Math.max(projectionX, projectionY);

        double dx = deltaX / maxProjection;
        double dy = deltaY / maxProjection;

        List<CustomPoint> points = new ArrayList<>();

        double x = x1 + 0.5 * Math.signum(dx);
        double y = y1 + 0.5 * Math.signum(dy);
        points.add(new CustomPoint(x, y, z, t, Color.BLACK));

        for (int i = 0; i <= maxProjection; i++) {
            x += dx;
            y += dy;
            points.add(new CustomPoint(x, y, z, t, Color.BLACK));
        }

        return points;
    }
}
