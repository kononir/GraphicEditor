package listener;

import algorithm.AlgorithmController;
import algorithm.AlgorithmGroup;
import algorithm.AlgorithmType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import util.CustomPoint;

import java.util.List;
import java.util.Optional;

public class MousePressAndDragExitListener {
    private static double startingMouseX;
    private static double startingMouseY;

    public static void setUp(Canvas canvas, ToggleGroup toggleGroup) {
        canvas.setOnMousePressed(event -> {
            startingMouseX = event.getX();
            startingMouseY = event.getY();
        });

        canvas.setOnMouseReleased(event -> {
            Optional<Toggle> selected = Optional.ofNullable(toggleGroup.getSelectedToggle());
            selected.ifPresent(toggle -> {
                AlgorithmType selectedAlgorithm = (AlgorithmType) toggle.getUserData();
                AlgorithmGroup selectedAlgorithmGroup = selectedAlgorithm.getGroup();

                if (selectedAlgorithmGroup.equals(AlgorithmGroup.LINE_SEGMENT_ALGORITHMS)) {
                    AlgorithmController controller = new AlgorithmController();
                    List<CustomPoint> linePoints = controller.controlGeneratingLineSegmentPoints(
                            selectedAlgorithm,
                            new CustomPoint(startingMouseX, startingMouseY, 0, 0, Color.BLACK),
                            new CustomPoint(event.getX(), event.getY(), 0, 0, Color.BLACK)
                    );

                    /* drawing */
                    for (CustomPoint point : linePoints) {
                        canvas.getGraphicsContext2D().getPixelWriter().setColor(
                                (int) point.getX(), (int) point.getY(), point.getColor()
                        );
                    }
                }
            });
        });
    }
}
