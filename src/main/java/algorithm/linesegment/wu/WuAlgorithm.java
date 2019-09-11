package algorithm.linesegment.wu;

import algorithm.linesegment.LineSegmentAlgorithm;
import javafx.scene.paint.Color;
import model.CustomPoint;
import algorithm.linesegment.wu.quadrant.WuQuadrant;
import algorithm.linesegment.wu.quadrant.WuQuadrantFactory;

import java.util.ArrayList;
import java.util.List;

public class WuAlgorithm implements LineSegmentAlgorithm {
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

        double maxProjection = Math.max(projectionX, projectionY);
        double minProjection = Math.min(projectionX, projectionY);

        double tangentAngle =  minProjection / maxProjection;

        List<CustomPoint> points = new ArrayList<>();
        points.add(new CustomPoint(x1, y1, z, t, Color.BLACK));

        WuQuadrant quadrant = new WuQuadrantFactory().create(x1, y1, z, t, tangentAngle, deltaX, deltaY);
        for (int i = 1; i <= maxProjection; i++) {
            points.addAll(quadrant.generateNextPoints());
        }

        return points;
    }
}
