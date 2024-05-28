package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private static final String CHECK_CONTAINS_CAPTION = "contains";
    private static final String CHECK_MIN_LENGTH_CAPTION = "minLength";

    public StringSchema minLength(int min) {
        addCheck(CHECK_MIN_LENGTH_CAPTION, checkValue -> {
            if (checkValue == null) {
                return true;
            }
            return checkValue.toString().length() >= min;
        });
        return this;
    }

    public StringSchema contains(String contain) {
        addCheck(CHECK_CONTAINS_CAPTION, checkValue -> {
            if (checkValue == null) {
                return true;
            }
            return checkValue.toString().contains(contain);
        });
        return this;
    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }
}
