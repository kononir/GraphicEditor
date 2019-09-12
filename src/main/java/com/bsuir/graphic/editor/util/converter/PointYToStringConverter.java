package com.bsuir.graphic.editor.util.converter;

import javafx.util.StringConverter;
import com.bsuir.graphic.editor.model.CustomPoint;

public class PointYToStringConverter extends StringConverter<CustomPoint> {

    @Override
    public String toString(CustomPoint object) {
        String converted;
        if (object != null) {
            converted = String.valueOf(object.getY());
        } else {
            converted = "";
        }

        return converted;
    }

    @Override
    public CustomPoint fromString(String string) {
        return new CustomPoint(0, Double.parseDouble(string), 0, 0, null);
    }
}
