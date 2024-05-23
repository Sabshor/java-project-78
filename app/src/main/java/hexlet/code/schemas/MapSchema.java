package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<T> extends BaseSchema<Map<String, T>> {
    public Integer sizeMap;

    public Map<String, BaseSchema<T>> rules = new HashMap<>();

    public MapSchema sizeof(int size) {
        sizeMap = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        rules = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Object checkValue) {
        boolean isValidate = true;
        HashMap<?, ?> map = (HashMap<?, ?>) checkValue;
        if (map == null) {
            return !isRequired();
        }
        if (sizeMap != null) {
            isValidate = map.size() == sizeMap;
        }
        if (isValidate) {
            if (rules != null) {
                isValidate = rules.entrySet().stream()
                        .allMatch((e) -> {
                            BaseSchema<T> rule = e.getValue();
                            return rule.isValid(map.get(e.getKey()));
                        });
            }
        }
        return isValidate;
    }
}
