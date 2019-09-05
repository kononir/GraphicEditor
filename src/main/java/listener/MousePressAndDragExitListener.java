package listener;

import algorithm.AlgorithmController;
import algorithm.AlgorithmGroup;
import algorithm.AlgorithmType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleGroup;
import util.CustomPoint;

import java.util.List;

public class MousePressAndDragExitListener {
    private static double startingMouseX;
    private static double startingMouseY;

    public static void setUp(Canvas canvas, ToggleGroup toggleGroup) {
        canvas.setOnMousePressed(event -> {
            startingMouseX = event.getScreenX();
            startingMouseY = event.getScreenY();
        });

        canvas.setOnMouseReleased(event -> {
            AlgorithmType selectedAlgorithm = (AlgorithmType) toggleGroup.getSelectedToggle().getUserData();
            AlgorithmGroup selectedAlgorithmGroup = selectedAlgorithm.getGroup();

            if (selectedAlgorithmGroup.equals(AlgorithmGroup.LINE_SEGMENT_ALGORITHMS)) {
                AlgorithmController controller = new AlgorithmController();
                List<CustomPoint> linePoints = controller.controlGeneratingLineSegmentPoints(
                        selectedAlgorithm,
                        new CustomPoint(startingMouseX, startingMouseY, 0, 0),
                        new CustomPoint(event.getScreenX(), event.getScreenY(), 0, 0)
                );

                /* drawing */
            }
        });
    }
}
