package hexlet.code.schemes;

public class StringSchema extends BaseSchema<StringSchema> {
    private int min;
    private boolean isRequired;
    private String subString = "";

    public StringSchema minLength(int length) {
        this.min = length;
        return this;
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema contains(String contain) {
        this.subString = contain;
        return this;
    }

    @Override
    public boolean isValid(Object checkValue) {
        boolean isValidate = true;
        String textValidate = checkValue == null ? "" : checkValue.toString();
        if ((min > 0) && (!textValidate.isEmpty())) {
            isValidate = textValidate.length() >= min;
        }
        if (isValidate) {
            if (!subString.isEmpty()) {
                isValidate = textValidate.contains(subString);
            }
        }
        if (isValidate) {
            if (isRequired) {
                isValidate = !textValidate.isEmpty();
            }
        }
        return isValidate;
    }

    public String toString() {
        return "StringSchema with min=" + min + "; isRequired=" + isRequired + "; contains=" + subString;
    }
}
