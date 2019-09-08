package ui;

import algorithm.AlgorithmType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listener.MousePressAndDragExitListener;

import java.util.List;
import java.util.Optional;

public class MainWindow {
    private static final int TOOL_BUTTONS_SIZE = 150;

    private DebugComponent debugComponent;
    private Canvas canvas = new Canvas();
    private ToggleGroup tools = new ToggleGroup();

    public MainWindow(Stage primaryStage) {
        MenuItem ddaMenuItem = new MenuItem(AlgorithmType.DDA.getName());
        ddaMenuItem.setOnAction(event -> selectToggle(AlgorithmType.DDA));

        MenuItem bresenhamMenuItem = new MenuItem(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamMenuItem.setOnAction(event -> selectToggle(AlgorithmType.BRESENHAM_ALGORITHM));

        MenuItem wuMenuItem = new MenuItem(AlgorithmType.WU_ALGORITHM.getName());
        wuMenuItem.setOnAction(event -> selectToggle(AlgorithmType.WU_ALGORITHM));

        Menu lineSegmentMenu = new Menu("Line segment");
        lineSegmentMenu.getItems().addAll(ddaMenuItem, bresenhamMenuItem, wuMenuItem);

        MenuBar menuBar = new MenuBar(lineSegmentMenu);

        ToggleButton ddaButton = new ToggleButton(AlgorithmType.DDA.getName());
        ddaButton.setOnTouchPressed(event -> {

        });
        ddaButton.setToggleGroup(tools);
        ddaButton.setUserData(AlgorithmType.DDA);
        ddaButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        ToggleButton bresenhamButton = new ToggleButton(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamButton.setToggleGroup(tools);
        bresenhamButton.setUserData(AlgorithmType.BRESENHAM_ALGORITHM);
        bresenhamButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        ToggleButton wuButton = new ToggleButton(AlgorithmType.WU_ALGORITHM.getName());
        wuButton.setToggleGroup(tools);
        wuButton.setUserData(AlgorithmType.WU_ALGORITHM);
        wuButton.setPrefWidth(TOOL_BUTTONS_SIZE);

        Label lineSegmentLabel = new Label("Line segment");

        VBox lineSegmentVBox = new VBox(ddaButton, bresenhamButton, wuButton, lineSegmentLabel);
        lineSegmentVBox.setSpacing(5);

        Button eraserButton = new Button("Eraser");
        eraserButton.setGraphic(new ImageView(new Image("img/eraser.png")));
        eraserButton.setOnAction(event -> fillCanvas());

        ToolBar toolBar = new ToolBar(lineSegmentVBox, new Separator(), eraserButton);

        canvas.setHeight(500);
        canvas.setWidth(700);
        fillCanvas();

        MousePressAndDragExitListener.setUp(canvas, tools);

        TableView debugTable = new TableView();
        debugTable.setPrefSize(300, 450);

        Button prevStepButton = new Button("Prev");
        Button nextStepButton = new Button("Next");

        debugComponent = new DebugComponent(debugTable, prevStepButton, nextStepButton);
        HBox debugButtonsHBox = new HBox(prevStepButton, nextStepButton);
        debugButtonsHBox.setSpacing(10);
        debugButtonsHBox.setAlignment(Pos.CENTER);

        VBox debugComponentVBox = new VBox(debugTable, debugButtonsHBox);
        debugComponentVBox.setSpacing(10);

        AnchorPane root = new AnchorPane(menuBar, toolBar, canvas, debugComponentVBox);
        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(toolBar, 25.0);
        AnchorPane.setLeftAnchor(toolBar, 0.0);
        AnchorPane.setRightAnchor(toolBar, 0.0);
        AnchorPane.setTopAnchor(canvas, 144.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 300.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setTopAnchor(debugComponentVBox, 144.0);
        AnchorPane.setRightAnchor(debugComponentVBox, 0.0);
        AnchorPane.setBottomAnchor(debugComponentVBox, 0.0);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fillCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /* selecting toggleButton after clicking to item */
    private void selectToggle(AlgorithmType algorithmType) {
        List<Toggle> toggles = tools.getToggles();
        Optional<Toggle> optionalToggle = toggles.stream()
                .filter(toggle -> algorithmType.equals(toggle.getUserData()))
                .findFirst();
        optionalToggle.ifPresent(toggle -> toggle.setSelected(true));
    }
}
