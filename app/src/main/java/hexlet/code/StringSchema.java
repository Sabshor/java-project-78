package hexlet.code;

public class StringSchema implements ValidateSchema {
    //private StringSchema stringSchema;
    private int min;
    private boolean isRequired;
    private String subString = "";

    public StringSchema minLength(int length) {
        this.min = length;
        return this;
    }

    public StringSchema required() {
        //StringSchema stringSchema = this;
        this.isRequired = true;
        return this;
    }

    public StringSchema contains(String contain) {
        //StringSchema stringSchema = this;
        this.subString = contain;
        return this;
    }

    public boolean isValid(String text) {
        boolean isValidate = true;
        String textValidate = text == null ? "" : text;
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
