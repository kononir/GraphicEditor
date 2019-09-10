package ui.debug;

import algorithm.AlgorithmType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import util.DdaAlgorithmDebugInfo;

public class DebugTableBuilder {
    public void build(TableView debugTable, AlgorithmType algorithmType) {
        switch (algorithmType) {
            case DDA:
                TableColumn<DdaAlgorithmDebugInfo, Integer> iCol = new TableColumn<>("i");
                TableColumn<DdaAlgorithmDebugInfo, Double> xCol = new TableColumn<>("x");
                TableColumn<DdaAlgorithmDebugInfo, Double> yCol = new TableColumn<>("y");

                iCol.setCellValueFactory(new PropertyValueFactory<>("stepNumber"));
                xCol.setCellValueFactory(new PropertyValueFactory<>("pointX"));
                yCol.setCellValueFactory(new PropertyValueFactory<>("pointY"));

                iCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                xCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
                yCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

                ObservableList<TableColumn> columns = debugTable.getColumns();
                columns.clear();
                columns.addAll(iCol, xCol, yCol);

                debugTable.setItems(FXCollections.observableArrayList());

                break;
            case BRESENHAM_ALGORITHM:
                break;
            case WU_ALGORITHM:
                break;
        }
    }
}
