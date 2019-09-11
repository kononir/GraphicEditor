package ui.debug;

import algorithm.AlgorithmType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.CustomPoint;
import util.converter.PointXToStringConverter;
import util.converter.PointYToStringConverter;
import util.debug.BresenhamAlgorithmDebugInfo;
import util.debug.DdaAlgorithmDebugInfo;

import java.util.Arrays;
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
                // WIP
                newColumns = null;
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

        iCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
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

        iCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        eCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        xCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointXToStringConverter()));
        yCol.setCellFactory(TextFieldTableCell.forTableColumn(new PointYToStringConverter()));
        adjustedECol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        return Arrays.asList(iCol, eCol, xCol, yCol, adjustedECol);
    }
}
