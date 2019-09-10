package ui;

import algorithm.AlgorithmType;
import algorithm.linesegment.dda.DdaDebugController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import listener.MousePressAndDragExitListener;
import model.CustomPoint;
import ui.debug.DebugAlert;
import ui.debug.DebugButtonType;
import ui.debug.DebugTableBuilder;
import ui.debug.DebugTextFieldType;
import util.DdaAlgorithmDebugInfo;

import java.util.*;

public class MainWindow {
    private static final int TOOL_BUTTONS_SIZE = 150;
    private static final int DEBUG_TEXT_FIELD_SIZE = 100;

    private CanvasDrawer canvasDrawer;
    private ToggleGroup tools = new ToggleGroup();

    private ToolBar toolBar = new ToolBar();
    private MenuBar menuBar = new MenuBar();

    private TableView debugTable = new TableView();
    private GridPane debugInputGridPane = new GridPane();
    private Map<DebugButtonType, Button> debugButtonsMap = new HashMap<>();
    private Map<DebugTextFieldType, TextField> debugTextFieldMap = new HashMap<>();

    private DdaDebugController ddaDebugController = new DdaDebugController();

    public MainWindow(Stage primaryStage) {
        MenuItem ddaMenuItem = new MenuItem(AlgorithmType.DDA.getName());
        ddaMenuItem.setOnAction(event -> selectToggle(AlgorithmType.DDA));

        MenuItem bresenhamMenuItem = new MenuItem(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamMenuItem.setOnAction(event -> selectToggle(AlgorithmType.BRESENHAM_ALGORITHM));

        MenuItem wuMenuItem = new MenuItem(AlgorithmType.WU_ALGORITHM.getName());
        wuMenuItem.setOnAction(event -> selectToggle(AlgorithmType.WU_ALGORITHM));

        Menu lineSegmentMenu = new Menu("Line segment");
        lineSegmentMenu.getItems().addAll(ddaMenuItem, bresenhamMenuItem, wuMenuItem);

        menuBar.getMenus().add(lineSegmentMenu);

        ToggleButton ddaButton = new ToggleButton(AlgorithmType.DDA.getName());
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
        eraserButton.setOnAction(event -> canvasDrawer.fillCanvas());

        ToggleButton debugButton = new ToggleButton("Debug");
        debugButton.setGraphic(new ImageView(new Image("img/debug.png")));
        debugButton.setOnAction(event -> {
            Optional<Toggle> selected = Optional.ofNullable(tools.getSelectedToggle());
            selected.ifPresent(toggle -> {
                if (debugButton.isSelected()) {
                    DebugTableBuilder tableBuilder = new DebugTableBuilder();
                    tableBuilder.build(debugTable, (AlgorithmType) toggle.getUserData());

                    Button startDebugButton = debugButtonsMap.get(DebugButtonType.START_DEBUG);

                    showAllFromMainStream(Arrays.asList(debugTable, debugInputGridPane, startDebugButton));

                    startDebugButton.setOnAction(event1 -> {
                        /* Disable menu and tool bars */
                        menuBar.setDisable(true);
                        toolBar.setDisable(true);

                        hideAllFromMainStream(Arrays.asList(startDebugButton, debugInputGridPane));

                        TextField x1TextField = debugTextFieldMap.get(DebugTextFieldType.X1);
                        TextField y1TextField = debugTextFieldMap.get(DebugTextFieldType.Y1);
                        TextField x2TextField = debugTextFieldMap.get(DebugTextFieldType.X2);
                        TextField y2TextField = debugTextFieldMap.get(DebugTextFieldType.Y2);
                        ddaDebugController.controlStartingDebug(
                                new CustomPoint(
                                        Double.parseDouble(x1TextField.getText()),
                                        Double.parseDouble(y1TextField.getText()),
                                        0,
                                        0,
                                        Color.BLACK
                                ),
                                new CustomPoint(
                                        Double.parseDouble(x2TextField.getText()),
                                        Double.parseDouble(y2TextField.getText()),
                                        0,
                                        0,
                                        Color.BLACK
                                )
                        );

                        Button prevStepButton = debugButtonsMap.get(DebugButtonType.PREV_STEP);
                        showAtMainStream(prevStepButton);
                        prevStepButton.setOnAction(event2 -> {
                            CustomPoint currPoint = ddaDebugController.controlPrevStep();
                            if (currPoint != null) {
                                /* deleting last row from table */
                                ObservableList debugList = debugTable.getItems();
                                int rowsNumber = debugList.size();
                                debugList.remove(rowsNumber - 1, rowsNumber);
                                /* deleting curr point from canvas */
                                canvasDrawer.drawPoint(
                                        new CustomPoint(
                                                currPoint.getX(),
                                                currPoint.getY(),
                                                currPoint.getZ(),
                                                currPoint.getT(),
                                                Color.WHITE
                                        )
                                );
                            } else {
                                new DebugAlert().show("Reached first step!");
                            }
                        });

                        Button nextStepButton = debugButtonsMap.get(DebugButtonType.NEXT_STEP);
                        showAtMainStream(nextStepButton);
                        nextStepButton.setOnAction(event2 -> {
                            DdaAlgorithmDebugInfo info = ddaDebugController.controlNextStep();

                            /* adding new info to table */
                            debugTable.getItems().add(info);
                            /* drawing point */
                            canvasDrawer.drawPoint(info.getPoint());
                        });

                        Button finishDebugButton = debugButtonsMap.get(DebugButtonType.FINISH_DEBUG);
                        showAtMainStream(finishDebugButton);
                        finishDebugButton.setOnAction(event2 -> finishDebug());
                    });
                } else {
                    finishDebug();
                }
            });
        });

        toolBar.getItems().addAll(lineSegmentVBox, new Separator(), eraserButton, new Separator(), debugButton);

        Canvas canvas = new Canvas();
        canvas.setHeight(500);
        canvas.setWidth(700);

        MousePressAndDragExitListener.setUp(canvas, tools);

        canvasDrawer = new CanvasDrawer(canvas);
        canvasDrawer.fillCanvas();

        debugTable = new TableView();
        debugTable.setPrefSize(300, 400);

        Button prevStepButton = new Button("Prev");
        debugButtonsMap.put(DebugButtonType.PREV_STEP, prevStepButton);

        Button nextStepButton = new Button("Next");
        debugButtonsMap.put(DebugButtonType.NEXT_STEP, nextStepButton);

        HBox firstLineDebugButtonsHBox = new HBox(prevStepButton, nextStepButton);
        firstLineDebugButtonsHBox.setSpacing(10);
        firstLineDebugButtonsHBox.setAlignment(Pos.CENTER);

        Button startDebugButton = new Button("Start debug");
        debugButtonsMap.put(DebugButtonType.START_DEBUG, startDebugButton);

        Button finishDebugButton = new Button("Finish debug");
        debugButtonsMap.put(DebugButtonType.FINISH_DEBUG, finishDebugButton);

        HBox secondLineDebugButtonsHBox = new HBox(startDebugButton, finishDebugButton);
        secondLineDebugButtonsHBox.setSpacing(10);
        secondLineDebugButtonsHBox.setAlignment(Pos.CENTER);

        Label x1Label = new Label("x1:");
        x1Label.setTextAlignment(TextAlignment.RIGHT);
        TextField x1TextField = new TextField();
        x1TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
        debugTextFieldMap.put(DebugTextFieldType.X1, x1TextField);

        Label y1Label = new Label("y1:");
        y1Label.setTextAlignment(TextAlignment.RIGHT);
        TextField y1TextField = new TextField();
        y1TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
        debugTextFieldMap.put(DebugTextFieldType.Y1, y1TextField);

        Label x2Label = new Label("x2:");
        x2Label.setTextAlignment(TextAlignment.RIGHT);
        TextField x2TextField = new TextField();
        x2TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
        debugTextFieldMap.put(DebugTextFieldType.X2, x2TextField);

        Label y2Label = new Label("y2:");
        y2Label.setTextAlignment(TextAlignment.RIGHT);
        TextField y2TextField = new TextField();
        y2TextField.setPrefWidth(DEBUG_TEXT_FIELD_SIZE);
        debugTextFieldMap.put(DebugTextFieldType.Y2, y2TextField);

        debugInputGridPane.setPrefSize(300, 20);
        debugInputGridPane.setPadding(new Insets(0, 5, 0, 5));
        debugInputGridPane.setHgap(10);
        debugInputGridPane.setVgap(5);

        debugInputGridPane.add(x1Label, 0, 0);
        debugInputGridPane.add(x1TextField, 1, 0);
        debugInputGridPane.add(y1Label, 2, 0);
        debugInputGridPane.add(y1TextField, 3, 0);
        debugInputGridPane.add(x2Label, 0, 1);
        debugInputGridPane.add(x2TextField, 1, 1);
        debugInputGridPane.add(y2Label, 2, 1);
        debugInputGridPane.add(y2TextField, 3, 1);

        debugInputGridPane.setAlignment(Pos.CENTER);

        VBox debugActiveArea = new VBox(debugInputGridPane, firstLineDebugButtonsHBox, secondLineDebugButtonsHBox);
        debugActiveArea.setSpacing(5);

        VBox debugComponentVBox = new VBox(debugTable, debugActiveArea);
        debugComponentVBox.setSpacing(5);

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

        hideAllFromMainStream(Arrays.asList(
                debugTable, debugInputGridPane, startDebugButton, finishDebugButton, prevStepButton, nextStepButton
        ));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* selecting toggleButton after clicking to item */
    private void selectToggle(AlgorithmType algorithmType) {
        List<Toggle> toggles = tools.getToggles();
        Optional<Toggle> optionalToggle = toggles.stream()
                .filter(toggle -> algorithmType.equals(toggle.getUserData()))
                .findFirst();
        optionalToggle.ifPresent(toggle -> toggle.setSelected(true));
    }

    private void hideFromMainStream(Node node) {
        node.setManaged(false);
        node.setVisible(false);
    }

    private void showAtMainStream(Node node) {
        node.setManaged(true);
        node.setVisible(true);
    }

    private void hideAllFromMainStream(List<Node> nodes) {
        for (Node node : nodes) {
            hideFromMainStream(node);
        }
    }

    private void showAllFromMainStream(List<Node> nodes) {
        for (Node node : nodes) {
            showAtMainStream(node);
        }
    }

    private void finishDebug() {
        Button nextStepButton = debugButtonsMap.get(DebugButtonType.NEXT_STEP);
        Button prevStepButton = debugButtonsMap.get(DebugButtonType.PREV_STEP);
        Button finishDebugButton = debugButtonsMap.get(DebugButtonType.FINISH_DEBUG);

        hideAllFromMainStream(Arrays.asList(
                debugTable, debugInputGridPane, nextStepButton, prevStepButton, finishDebugButton)
        );

        /* Undisable bars */
        menuBar.setDisable(false);
        toolBar.setDisable(false);
    }
}
