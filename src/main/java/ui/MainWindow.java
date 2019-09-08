package ui;

import algorithm.AlgorithmType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listener.MousePressAndDragExitListener;

public class MainWindow {
    private static final int TOOL_BUTTONS_SIZE = 150;

    public MainWindow(Stage primaryStage) {
        MenuItem ddaMenuItem = new MenuItem(AlgorithmType.DDA.getName());
        MenuItem bresenhamMenuItem = new MenuItem(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        MenuItem wuMenuItem = new MenuItem(AlgorithmType.WU_ALGORITHM.getName());
        /* add choosing toggleButton after clicking to item */

        Menu lineSegmentMenu = new Menu("Line segment");
        lineSegmentMenu.getItems().addAll(ddaMenuItem, bresenhamMenuItem, wuMenuItem);

        MenuBar menuBar = new MenuBar(lineSegmentMenu);

        ToggleGroup toggleGroup = new ToggleGroup();

        ToggleButton ddaButton = new ToggleButton(AlgorithmType.DDA.getName());
        ddaButton.setToggleGroup(toggleGroup);
        ddaButton.setUserData(AlgorithmType.DDA);
        ddaButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        ToggleButton bresenhamButton = new ToggleButton(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamButton.setToggleGroup(toggleGroup);
        bresenhamButton.setUserData(AlgorithmType.BRESENHAM_ALGORITHM);
        bresenhamButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        ToggleButton wuButton = new ToggleButton(AlgorithmType.WU_ALGORITHM.getName());
        wuButton.setToggleGroup(toggleGroup);
        wuButton.setUserData(AlgorithmType.WU_ALGORITHM);
        wuButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        Label lineSegmentLabel = new Label("Line segment");

        VBox lineSegmentVBox = new VBox(ddaButton, bresenhamButton, wuButton, lineSegmentLabel);
        lineSegmentVBox.setSpacing(5);

        ToolBar toolBar = new ToolBar(lineSegmentVBox, new Separator());

        Canvas canvas = new Canvas();
        canvas.setHeight(500);
        canvas.setWidth(1000);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        MousePressAndDragExitListener.setUp(canvas, toggleGroup);

        AnchorPane root = new AnchorPane(menuBar, toolBar, canvas);
        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(toolBar, 25.0);
        AnchorPane.setLeftAnchor(toolBar, 0.0);
        AnchorPane.setRightAnchor(toolBar, 0.0);
        AnchorPane.setTopAnchor(canvas, 144.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
