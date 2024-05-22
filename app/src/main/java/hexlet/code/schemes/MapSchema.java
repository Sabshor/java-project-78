package hexlet.code.schemes;

import java.util.HashMap;

public class MapSchema extends BaseSchema<MapSchema> {
    public Integer sizeMap;

    public MapSchema sizeof(int size) {
        sizeMap = size;
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
        return isValidate;
    }
}
