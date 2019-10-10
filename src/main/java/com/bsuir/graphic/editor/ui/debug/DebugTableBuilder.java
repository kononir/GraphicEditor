package com.bsuir.graphic.editor.ui.debug;

import com.bsuir.graphic.editor.algorithm.AlgorithmType;
import com.bsuir.graphic.editor.model.CustomPoint;
import com.bsuir.graphic.editor.util.converter.PointIntensityToStringConverter;
import com.bsuir.graphic.editor.util.converter.PointXToStringConverter;
import com.bsuir.graphic.editor.util.converter.PointYToStringConverter;
import com.bsuir.graphic.editor.util.debug.BresenhamAlgorithmDebugInfo;
import com.bsuir.graphic.editor.util.debug.DdaAlgorithmDebugInfo;
import com.bsuir.graphic.editor.util.debug.WuAlgorithmDebugInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DebugTableBuilder {
    public void build(TableView debugTable, AlgorithmType algorithmType) {
        List<TableColumn> newColumns;
        switch (algorithmType) {
            case DDA:
                newColumns = buildDdaColumns();
                break;
            case BRESENHAM_ALGORITHM:
                newColumns = buildBresenhamColumns();
                break;
            case WU_ALGORITHM:
                newColumns = buildWuColumns();
                break;
            case CIRCLE_GENERATION_ALGORITHM:
            case ELLIPSE_GENERATION_ALGORITHM:
                newColumns = Collections.emptyList();
                break;
            default:
                throw new EnumConstantNotPresentException(AlgorithmType.class, algorithmType.getName());
        }

        ObservableList<TableColumn> columns = debugTable.getColumns();
        columns.clear();
        columns.addAll(newColumns);

        debugTable.setItems(FXCollections.observableArrayList());
    }

    private List<TableColumn> buildDdaColumns() {
        TableColumn<DdaAlgorithmDebugInfo, Integer> iCol = new TableColumn<>("i");
        TableColumn<DdaAlgorithmDebugInfo, CustomPoint> xCol = new TableColumn<>("x");
        TableColumn<DdaAlgorithmDebugInfo, CustomPoint> yCol = new TableColumn<>("y");

        iCol.setCellValueFactory(new PropertyValueFactory<>("step"));
        xCol.setCellValueFactory(new PropertyValueFactory<>("point"));
        yCol.setCellValueFactory(new PropertyValueFactory<>("point"));

        xCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointXToStringConverter()));
        yCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointYToStringConverter()));

        return Arrays.asList(iCol, xCol, yCol);
    }

    private List<TableColumn> buildBresenhamColumns() {
        TableColumn<BresenhamAlgorithmDebugInfo, Integer> iCol = new TableColumn<>("i");
        TableColumn<BresenhamAlgorithmDebugInfo, Integer> eCol = new TableColumn<>("e");
        TableColumn<BresenhamAlgorithmDebugInfo, CustomPoint> xCol = new TableColumn<>("x");
        TableColumn<BresenhamAlgorithmDebugInfo, CustomPoint> yCol = new TableColumn<>("y");
        TableColumn<BresenhamAlgorithmDebugInfo, Integer> adjustedECol = new TableColumn<>("e'");

        iCol.setCellValueFactory(new PropertyValueFactory<>("step"));
        eCol.setCellValueFactory(new PropertyValueFactory<>("error"));
        xCol.setCellValueFactory(new PropertyValueFactory<>("point"));
        yCol.setCellValueFactory(new PropertyValueFactory<>("point"));
        adjustedECol.setCellValueFactory(new PropertyValueFactory<>("adjustedError"));

        xCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointXToStringConverter()));
        yCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointYToStringConverter()));

        return Arrays.asList(iCol, eCol, xCol, yCol, adjustedECol);
    }

    private List<TableColumn> buildWuColumns() {
        TableColumn<WuAlgorithmDebugInfo, Integer> iCol = new TableColumn<>("i");
        TableColumn<WuAlgorithmDebugInfo, Double> eCol = new TableColumn<>("e");
        TableColumn<WuAlgorithmDebugInfo, Double> adjustedECol = new TableColumn<>("e'");

        TableColumn<WuAlgorithmDebugInfo, CustomPoint> x1Col = new TableColumn<>("x1");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> y1Col = new TableColumn<>("y1");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> e1Col = new TableColumn<>("e1");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> point1Col = new TableColumn<>("Point 1");
        point1Col.getColumns().addAll(x1Col, y1Col, e1Col);

        TableColumn<WuAlgorithmDebugInfo, CustomPoint> x2Col = new TableColumn<>("x2");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> y2Col = new TableColumn<>("y2");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> e2Col = new TableColumn<>("e2");
        TableColumn<WuAlgorithmDebugInfo, CustomPoint> point2Col = new TableColumn<>("Point 2");
        point2Col.getColumns().addAll(x2Col, y2Col, e2Col);

        iCol.setCellValueFactory(new PropertyValueFactory<>("step"));
        eCol.setCellValueFactory(new PropertyValueFactory<>("error"));
        adjustedECol.setCellValueFactory(new PropertyValueFactory<>("adjustedError"));

        x1Col.setCellValueFactory(new PropertyValueFactory<>("point1"));
        y1Col.setCellValueFactory(new PropertyValueFactory<>("point1"));
        e1Col.setCellValueFactory(new PropertyValueFactory<>("point1"));

        x2Col.setCellValueFactory(new PropertyValueFactory<>("point2"));
        y2Col.setCellValueFactory(new PropertyValueFactory<>("point2"));
        e2Col.setCellValueFactory(new PropertyValueFactory<>("point2"));

        x1Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointXToStringConverter()));
        y1Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointYToStringConverter()));
        e1Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointIntensityToStringConverter()));

        x2Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointXToStringConverter()));
        y2Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointYToStringConverter()));
        e2Col.setCellFactory(TextFieldTableCell.forTableColumn(new PointIntensityToStringConverter()));

        return Arrays.asList(iCol, eCol, adjustedECol, point1Col, point2Col);
    }
}
