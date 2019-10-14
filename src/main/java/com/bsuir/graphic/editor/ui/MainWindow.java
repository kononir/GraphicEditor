package com.bsuir.graphic.editor.ui;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.algorithm.DebugControllerException;
import com.bsuir.graphic.editor.algorithm.linesegment.bresenham.BDebugController;
import com.bsuir.graphic.editor.algorithm.linesegment.dda.DdaDebugController;
import com.bsuir.graphic.editor.algorithm.linesegment.wu.WuDebugController;
import com.bsuir.graphic.editor.algorithm.secorderlines.FigureDebugController;
import com.bsuir.graphic.editor.listener.MousePressAndReleaseListener;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.ui.debug.*;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;
import com.bsuir.graphic.editor.util.debug.DdaAlgorithmDebugInfo;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;
import com.bsuir.graphic.editor.util.point.CanvasDrawer;
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
import javafx.stage.Stage;

import java.util.*;

public class MainWindow {
    private static final int TOOL_BUTTONS_SIZE = 150;

    private CanvasDrawer canvasDrawer;
    private ToggleGroup tools = new ToggleGroup();

    private ToolBar toolBar = new ToolBar();
    private MenuBar menuBar = new MenuBar();

    private TableView debugTable = new TableView();
    private GridPane debugInputGridPane = new GridPane();
    private Map<DebugButtonType, Button> debugButtonsMap = new EnumMap<>(DebugButtonType.class);

    private Map<ToolButtonType, ButtonBase> toolButtonsMap = new EnumMap<>(ToolButtonType.class);

    private DdaDebugController ddaDebugController = new DdaDebugController();
    private BDebugController bDebugController = new BDebugController();
    private WuDebugController wuDebugController = new WuDebugController();

    private FigureDebugController figureDebugController = new FigureDebugController();

    public MainWindow(Stage primaryStage) {
        MenuItem ddaMenuItem = new MenuItem(AlgorithmType.DDA.getName());
        ddaMenuItem.setOnAction(event -> {
            selectToggle(AlgorithmType.DDA);
            chooseDebugActionForAlgorithmButton();
        });

        MenuItem bresenhamMenuItem = new MenuItem(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamMenuItem.setOnAction(event -> {
            selectToggle(AlgorithmType.BRESENHAM_ALGORITHM);
            chooseDebugActionForAlgorithmButton();
        });

        MenuItem wuMenuItem = new MenuItem(AlgorithmType.WU_ALGORITHM.getName());
        wuMenuItem.setOnAction(event -> {
            selectToggle(AlgorithmType.WU_ALGORITHM);
            chooseDebugActionForAlgorithmButton();
        });

        Menu lineSegmentMenu = new Menu("Line segment");
        lineSegmentMenu.getItems().addAll(ddaMenuItem, bresenhamMenuItem, wuMenuItem);

        menuBar.getMenus().add(lineSegmentMenu);

        ToggleButton ddaButton = new ToggleButton(AlgorithmType.DDA.getName());
        ddaButton.setToggleGroup(tools);
        ddaButton.setUserData(AlgorithmType.DDA);
        ddaButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        ddaButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        ToggleButton bresenhamButton = new ToggleButton(AlgorithmType.BRESENHAM_ALGORITHM.getName());
        bresenhamButton.setToggleGroup(tools);
        bresenhamButton.setUserData(AlgorithmType.BRESENHAM_ALGORITHM);
        bresenhamButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        bresenhamButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        ToggleButton wuButton = new ToggleButton(AlgorithmType.WU_ALGORITHM.getName());
        wuButton.setToggleGroup(tools);
        wuButton.setUserData(AlgorithmType.WU_ALGORITHM);
        wuButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        wuButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        Label lineSegmentLabel = new Label("Line segment");

        VBox lineSegmentVBox = new VBox(ddaButton, bresenhamButton, wuButton, lineSegmentLabel);
        lineSegmentVBox.setSpacing(5);

        ToggleButton circleButton = new ToggleButton(AlgorithmType.CIRCLE_GENERATION_ALGORITHM.getName());
        circleButton.setToggleGroup(tools);
        circleButton.setUserData(AlgorithmType.CIRCLE_GENERATION_ALGORITHM);
        circleButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        circleButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        ToggleButton ellipseButton = new ToggleButton(AlgorithmType.ELLIPSE_GENERATION_ALGORITHM.getName());
        ellipseButton.setToggleGroup(tools);
        ellipseButton.setUserData(AlgorithmType.ELLIPSE_GENERATION_ALGORITHM);
        ellipseButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        ellipseButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        ToggleButton hyperboleButton = new ToggleButton(AlgorithmType.HYPERBOLE_GENERATION_ALGORITHM.getName());
        hyperboleButton.setToggleGroup(tools);
        hyperboleButton.setUserData(AlgorithmType.HYPERBOLE_GENERATION_ALGORITHM);
        hyperboleButton.setPrefWidth(TOOL_BUTTONS_SIZE);
        hyperboleButton.setOnAction(event -> chooseDebugActionForAlgorithmButton());

        Label secondOrderLinesLabel = new Label("Second order lines");

        VBox secondOrderLinesVBox = new VBox(circleButton, ellipseButton, hyperboleButton, secondOrderLinesLabel);
        secondOrderLinesVBox.setSpacing(5);

        Button eraserButton = new Button("Eraser");
        eraserButton.setGraphic(new ImageView(new Image("img/eraser.png")));
        eraserButton.setOnAction(event -> canvasDrawer.fillCanvas());

        ToggleButton debugButton = new ToggleButton("Debug");
        toolButtonsMap.put(ToolButtonType.DEBUG, debugButton);
        debugButton.setGraphic(new ImageView(new Image("img/debug.png")));
        debugButton.setOnAction(event -> chooseDebugActionForDebugButton());

        toolBar.getItems().addAll(
                lineSegmentVBox,
                new Separator(),
                secondOrderLinesVBox,
                new Separator(),
                eraserButton,
                new Separator(),
                debugButton
        );

        Canvas canvas = new Canvas();
        canvas.setHeight(506);
        canvas.setWidth(1000);

        MousePressAndReleaseListener.setUp(canvas, tools, debugButton);

        canvasDrawer = new CanvasDrawer(canvas);
        canvasDrawer.fillCanvas();

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

        debugInputGridPane.setPrefSize(300, 20);
        debugInputGridPane.setPadding(new Insets(0, 5, 0, 5));
        debugInputGridPane.setHgap(10);
        debugInputGridPane.setVgap(5);
        debugInputGridPane.setAlignment(Pos.CENTER);

        VBox debugActiveArea = new VBox(debugInputGridPane, firstLineDebugButtonsHBox, secondLineDebugButtonsHBox);
        debugActiveArea.setSpacing(5);

        VBox debugComponentVBox = new VBox();
        debugComponentVBox.getChildren().addAll(debugTable, debugActiveArea);
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
        AnchorPane.setRightAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setTopAnchor(debugComponentVBox, 144.0);
        AnchorPane.setRightAnchor(debugComponentVBox, 0.0);
        AnchorPane.setBottomAnchor(debugComponentVBox, 0.0);

        hideAllFromMainStream(Arrays.asList(
                debugTable, debugInputGridPane, startDebugButton, finishDebugButton, prevStepButton, nextStepButton
        ));

        Scene scene = new Scene(root, 1000, 650);
        scene.widthProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.setWidth(scene.getWidth());
            canvasDrawer.fillCanvas();
        }));
        scene.heightProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.setHeight(scene.getHeight() - 144);
            canvasDrawer.fillCanvas();
        }));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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

    private void chooseDebugActionForAlgorithmButton() {
        ToggleButton debugButton = (ToggleButton) toolButtonsMap.get(ToolButtonType.DEBUG);
        Optional<Toggle> selected = Optional.ofNullable(tools.getSelectedToggle());
        if (debugButton.isSelected()) {
            if (selected.isPresent()) {
                showDebugComponent(selected.get());
            } else {
                debugButton.setSelected(false);
                new DebugAlert().show("Algorithm is not selected!");
                finishDebug();
            }
        }
    }

    private void chooseDebugActionForDebugButton() {
        ToggleButton debugButton = (ToggleButton) toolButtonsMap.get(ToolButtonType.DEBUG);
        Optional<Toggle> selected = Optional.ofNullable(tools.getSelectedToggle());
        if (debugButton.isSelected()) {
            if (selected.isPresent()) {
                showDebugComponent(selected.get());
            } else {
                debugButton.setSelected(false);
                new DebugAlert().show("Algorithm is not selected!");
            }
        } else {
            finishDebug();
        }
    }

    private void showDebugComponent(Toggle selectedToggle) {
        AlgorithmType algorithmType = (AlgorithmType) selectedToggle.getUserData();

        DebugTableBuilder tableBuilder = new DebugTableBuilder();
        tableBuilder.build(debugTable, algorithmType);

        Button startDebugButton = debugButtonsMap.get(DebugButtonType.START_DEBUG);

        DebugInputBuilder builder = new DebugInputBuilder();
        Map<DebugTextFieldType, TextField> debugTextFieldMap = builder.build(debugInputGridPane, algorithmType);

        showAllFromMainStream(Arrays.asList(debugTable, debugInputGridPane, startDebugButton));

        startDebugButton.setOnAction(event1 -> startDebug(algorithmType, debugTextFieldMap));
    }

    private void startDebug(AlgorithmType algorithmType, Map<DebugTextFieldType, TextField> debugTextFieldMap) {
        /* Disable menu and tool bars */
        menuBar.setDisable(true);
        toolBar.setDisable(true);

        Button startDebugButton = debugButtonsMap.get(DebugButtonType.START_DEBUG);
        hideAllFromMainStream(Arrays.asList(startDebugButton, debugInputGridPane));

        canvasDrawer.fillCanvas();
        canvasDrawer.drawGrid(CanvasDrawer.BIG_POINT_SIZE);

        switch (algorithmType.getGroup()) {
            case LINE_SEGMENT_ALGORITHMS:
                startLineSegmentDebug(algorithmType, debugTextFieldMap);
                break;
            case SEC_ORDER_LINE_ALGORITHMS:
                startSecondOrderLinesDebug(algorithmType, debugTextFieldMap);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
        }

        Button prevStepButton = debugButtonsMap.get(DebugButtonType.PREV_STEP);
        showAtMainStream(prevStepButton);
        prevStepButton.setOnAction(event2 -> prevStepOfDebug(algorithmType));

        Button nextStepButton = debugButtonsMap.get(DebugButtonType.NEXT_STEP);
        showAtMainStream(nextStepButton);
        nextStepButton.setOnAction(event2 -> nextStepOfDebug(algorithmType));

        Button finishDebugButton = debugButtonsMap.get(DebugButtonType.FINISH_DEBUG);
        showAtMainStream(finishDebugButton);
        finishDebugButton.setOnAction(event2 -> {
            canvasDrawer.fillCanvas();
            finishDebug();
        });
    }

    private void startLineSegmentDebug(AlgorithmType algorithmType,
                                       Map<DebugTextFieldType, TextField> debugTextFieldMap) {
        TextField x1TextField = debugTextFieldMap.get(DebugTextFieldType.X1);
        TextField y1TextField = debugTextFieldMap.get(DebugTextFieldType.Y1);
        TextField x2TextField = debugTextFieldMap.get(DebugTextFieldType.X2);
        TextField y2TextField = debugTextFieldMap.get(DebugTextFieldType.Y2);

        CustomPoint startingPoint = CustomPoint.simplePoint(
                Double.parseDouble(x1TextField.getText()),
                Double.parseDouble(y1TextField.getText()),
                Color.BLACK
        );
        CustomPoint endingPoint = CustomPoint.simplePoint(
                Double.parseDouble(x2TextField.getText()),
                Double.parseDouble(y2TextField.getText()),
                Color.BLACK
        );

        switch (algorithmType) {
            case DDA:
                ddaDebugController.controlStartingDebug(startingPoint, endingPoint);
                break;
            case BRESENHAM_ALGORITHM:
                bDebugController.controlStartingDebug(startingPoint, endingPoint);
                break;
            case WU_ALGORITHM:
                wuDebugController.controlStartingDebug(startingPoint, endingPoint);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
        }
    }

    private void startSecondOrderLinesDebug(AlgorithmType algorithmType,
                                            Map<DebugTextFieldType, TextField> debugTextFieldMap) {
        TextField xTextField = debugTextFieldMap.get(DebugTextFieldType.X);
        TextField yTextField = debugTextFieldMap.get(DebugTextFieldType.Y);

        CustomPoint centerPoint = CustomPoint.simplePoint(
                Double.parseDouble(xTextField.getText()),
                Double.parseDouble(yTextField.getText()),
                Color.BLACK
        );

        switch (algorithmType) {
            case CIRCLE_GENERATION_ALGORITHM:
                TextField radiusTextField = debugTextFieldMap.get(DebugTextFieldType.RADIUS);
                Integer radius = Integer.valueOf(radiusTextField.getText());
                figureDebugController.controlStartingDebug(centerPoint,
                        Collections.singletonList(radius), algorithmType);
                break;
            case ELLIPSE_GENERATION_ALGORITHM:
            case HYPERBOLE_GENERATION_ALGORITHM:
                TextField aEllipseTextField = debugTextFieldMap.get(DebugTextFieldType.A);
                Integer a = Integer.valueOf(aEllipseTextField.getText());
                TextField bEllipseTextField = debugTextFieldMap.get(DebugTextFieldType.B);
                Integer b = Integer.valueOf(bEllipseTextField.getText());
                figureDebugController.controlStartingDebug(centerPoint,
                        Arrays.asList(a, b), algorithmType);
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
        }
    }

    private void prevStepOfDebug(AlgorithmType algorithmType) {
        try {
            switch (algorithmType) {
                case DDA:
                    CustomPoint currPointDda = ddaDebugController.controlPrevStep();
                    deleteLastInfo();
                    canvasDrawer.deleteBigPoint(currPointDda);
                    break;
                case BRESENHAM_ALGORITHM:
                    CustomPoint currPointB = bDebugController.controlPrevStep();
                    deleteLastInfo();
                    canvasDrawer.deleteBigPoint(currPointB);
                    break;
                case WU_ALGORITHM:
                    List<CustomPoint> currPointsWu = wuDebugController.controlPrevStep();
                    deleteLastInfo();
                    for (CustomPoint pointWu : currPointsWu) {
                        if (pointWu != null) {
                            canvasDrawer.deleteBigPoint(pointWu);
                        }
                    }
                    break;
                case CIRCLE_GENERATION_ALGORITHM:
                case ELLIPSE_GENERATION_ALGORITHM:
                case HYPERBOLE_GENERATION_ALGORITHM:
                    CustomPoint currPointCircle = figureDebugController.controlPrevStep();
                    canvasDrawer.deleteBigPoint(currPointCircle);
                    break;
                default:
                    throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
            }
        } catch (DebugControllerException e) {
            new DebugAlert().show(e.getMessage());
        }

        canvasDrawer.drawGrid(10);
    }

    /* deleting last row from table */
    private void deleteLastInfo() {
        ObservableList debugList = debugTable.getItems();
        int rowsNumber = debugList.size();
        debugList.remove(rowsNumber - 1, rowsNumber);
    }

    private void nextStepOfDebug(AlgorithmType algorithmType) {
        try {
            switch (algorithmType) {
                case DDA:
                    DdaAlgorithmDebugInfo ddaInfo = ddaDebugController.controlNextStep();
                    debugTable.getItems().add(ddaInfo);
                    CustomPoint ddaPoint = ddaInfo.getPoint();
                    canvasDrawer.drawBigPoint(ddaPoint);
                    break;
                case BRESENHAM_ALGORITHM:
                    BresenhamAlgorithmDebugInfo bInfo = bDebugController.controlNextStep();
                    debugTable.getItems().add(bInfo);
                    CustomPoint bPoint = bInfo.getPoint();
                    canvasDrawer.drawBigPoint(bPoint);
                    break;
                case WU_ALGORITHM:
                    WuAlgorithmDebugInfo wuInfo = wuDebugController.controlNextStep();
                    debugTable.getItems().add(wuInfo);
                    CustomPoint wuPoint1 = wuInfo.getPoint1();
                    canvasDrawer.drawBigPoint(wuPoint1);
                    CustomPoint wuPoint2 = wuInfo.getPoint2();
                    if (wuPoint2 != null) {
                        canvasDrawer.drawBigPoint(wuPoint2);
                    }
                    break;
                case CIRCLE_GENERATION_ALGORITHM:
                case ELLIPSE_GENERATION_ALGORITHM:
                case HYPERBOLE_GENERATION_ALGORITHM:
                    CustomPoint circlePoint = figureDebugController.controlNextStep();
                    canvasDrawer.drawBigPoint(circlePoint);
                    break;
                default:
                    throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
            }
        } catch (DebugControllerException e) {
            new DebugAlert().show(e.getMessage());
        }
    }

    private void finishDebug() {
        Button nextStepButton = debugButtonsMap.get(DebugButtonType.NEXT_STEP);
        Button prevStepButton = debugButtonsMap.get(DebugButtonType.PREV_STEP);
        Button startDebugButton = debugButtonsMap.get(DebugButtonType.START_DEBUG);
        Button finishDebugButton = debugButtonsMap.get(DebugButtonType.FINISH_DEBUG);

        hideAllFromMainStream(Arrays.asList(
                debugTable, debugInputGridPane, nextStepButton, prevStepButton, startDebugButton, finishDebugButton)
        );

        ToggleButton debugButton = (ToggleButton) toolButtonsMap.get(ToolButtonType.DEBUG);
        debugButton.setSelected(false);

        /* Undisable bars */
        menuBar.setDisable(false);
        toolBar.setDisable(false);
    }
}
