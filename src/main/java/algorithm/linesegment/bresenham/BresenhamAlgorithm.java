package algorithm.linesegment.bresenham;

import algorithm.linesegment.LineSegmentAlgorithm;
import algorithm.linesegment.bresenham.quadrant.BQuadrant;
import algorithm.linesegment.bresenham.quadrant.BQuadrantFactory;
import javafx.scene.paint.Color;
import model.CustomPoint;

import java.util.ArrayList;
import java.util.List;

public class BresenhamAlgorithm implements LineSegmentAlgorithm {
    @Override
    public List<CustomPoint> generateLineSegment(CustomPoint startingPoint, CustomPoint endingPoint) {
        int x1 = (int) startingPoint.getX();
        int x2 = (int) endingPoint.getX();
        int y1 = (int) startingPoint.getY();
        int y2 = (int) endingPoint.getY();
        int z = (int) startingPoint.getZ();
        int t = (int) startingPoint.getT();

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int projectionX = Math.abs(deltaX);
        int projectionY = Math.abs(deltaY);
        int maxProjection = Math.max(projectionX, projectionY);
        int minProjection = Math.min(projectionX, projectionY);

        int initialE = 2 * minProjection - maxProjection;

        List<CustomPoint> points = new ArrayList<>();
        points.add(new CustomPoint(x1, y1, z, t, Color.BLACK));

        BQuadrant quadrant = new BQuadrantFactory().create(x1, y1, z, t, initialE, deltaX, deltaY);
        for (int i = 1; i <= maxProjection; i++) {
            points.add(quadrant.calculateNextPoint());
        }

        return points;
    }
}
