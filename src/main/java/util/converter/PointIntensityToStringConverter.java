package util.converter;

import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import model.CustomPoint;

public class PointIntensityToStringConverter extends StringConverter<CustomPoint> {
    @Override
    public String toString(CustomPoint object) {
        String converted;
        if (object != null) {
            converted = String.valueOf(object.getColor().getBrightness());
        } else {
            converted = "";
        }

        return converted;
    }

    @Override
    public CustomPoint fromString(String string) {
        return new CustomPoint(0, 0, 0, 0, Color.gray(Double.parseDouble(string)));
    }
}
