package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private static final String CHECK_SIZE_OF_CAPTION = "sizeof";
    private static final String CHECK_SHAPE_CAPTION = "shape";

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer sizeMap) {
        addCheck(CHECK_SIZE_OF_CAPTION, m -> ((Map<String, T>) m).size() == sizeMap);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(CHECK_SHAPE_CAPTION, checkValue -> {
            if (schemas == null) {
                return true;
            }
            HashMap<?, ?> map = (HashMap<?, ?>) checkValue;
            return schemas.entrySet().stream()
                    .allMatch((e) -> {
                        BaseSchema<T> rule = e.getValue();
                        return rule.isValid(map.get(e.getKey()));
                    });
        });
        return this;
    }
}
