package util.converter;

import javafx.util.StringConverter;
import model.CustomPoint;

public class PointXToStringConverter extends StringConverter<CustomPoint> {
    @Override
    public String toString(CustomPoint object) {
        return String.valueOf(object.getX());
    }

    @Override
    public CustomPoint fromString(String string) {
        return new CustomPoint(Double.parseDouble(string), 0, 0, 0, null);
    }
}
