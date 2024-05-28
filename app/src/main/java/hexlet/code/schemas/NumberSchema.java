package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private static final String CHECK_RANGE_CAPTION = "range";
    private static final String CHECK_POSITIVE_CAPTION = "positive";

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck(CHECK_POSITIVE_CAPTION, checkValue -> {
            if (checkValue == null) {
                return true;
            }
            return (Integer.parseInt(checkValue.toString()) > 0);
        });
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(CHECK_RANGE_CAPTION, checkValue -> {
            if (checkValue == null) {
                return true;
            }
            Integer value = Integer.parseInt(checkValue.toString());
            return (value >= min) && (value <= max);
        });
        return this;
    }
}
