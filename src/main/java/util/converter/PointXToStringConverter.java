package util.converter;

import javafx.util.StringConverter;
import model.CustomPoint;

public class PointXToStringConverter extends StringConverter<CustomPoint> {
    @Override
    public String toString(CustomPoint object) {
        String converted;
        if (object != null) {
            converted = String.valueOf(object.getX());
        } else {
            converted = "";
        }

        return converted;
    }

    @Override
    public CustomPoint fromString(String string) {
        return new CustomPoint(Double.parseDouble(string), 0, 0, 0, null);
    }
}
