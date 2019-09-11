package util.converter;

import javafx.util.StringConverter;
import model.CustomPoint;

public class PointYToStringConverter extends StringConverter<CustomPoint> {

    @Override
    public String toString(CustomPoint object) {
        return String.valueOf(object.getY());
    }

    @Override
    public CustomPoint fromString(String string) {
        return new CustomPoint(0, Double.parseDouble(string), 0, 0, null);
    }
}
