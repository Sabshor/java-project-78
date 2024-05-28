package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired;
    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    protected final void addCheck(String checkCaption, Predicate<Object> checkResult) {
        checks.put(checkCaption, checkResult);
    }

    public final boolean isValid(Object checkValue) {
        if (isRequired) {
            if ((checkValue == null) || ("".equals(checkValue))) {
                return false;
            }
        }

        return checks.entrySet().stream()
                .allMatch((e) -> e.getValue().test(checkValue));
    }
}
