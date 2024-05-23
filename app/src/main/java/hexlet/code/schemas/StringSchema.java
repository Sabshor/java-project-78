package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private int min;
    private String subString = "";

    public StringSchema minLength(int length) {
        this.min = length;
        return this;
    }

    public StringSchema contains(String contain) {
        this.subString = contain;
        return this;
    }
    @Override
    public StringSchema required() {
        return (StringSchema) super.required();
    }

    @Override
    public boolean isValid(Object checkValue) {
        boolean isValidate = true;
        String textValidate = checkValue == null ? "" : checkValue.toString();
        if (textValidate.isEmpty()) {
            return !isRequired();
        }
        if (min > 0) {
            isValidate = textValidate.length() >= min;
        }
        if (isValidate) {
            if (!subString.isEmpty()) {
                isValidate = textValidate.contains(subString);
            }
        }
        return isValidate;
    }

    public String toString() {
        return "StringSchema with min=" + min + "; isRequired=" + isRequired() + "; contains=" + subString;
    }
}
