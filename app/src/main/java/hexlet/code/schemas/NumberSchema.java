package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive;
    private Integer minRange;
    private Integer maxRange;

    @Override
    public NumberSchema required() {
        return (NumberSchema) super.required();
    }
    /*@Override
    public NumberSchema required() {
        super.isRequired = true;
        return this;
    }*/

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Object checkValue) {
        boolean isValidate = true;
        Integer value = checkValue == null ? null : Integer.valueOf(checkValue.toString());
        //Integer value = (Integer) checkValue;
        if (value == null) {
            return !isRequired;
        }
        if (isPositive) {
            isValidate = value > 0;
        }
        if (isValidate) {
            if (minRange != null) {
                isValidate = value >= minRange;
            }
        }
        if (isValidate) {
            if (maxRange != null) {
                isValidate = value <= maxRange;
            }
        }

        return isValidate;
    }

    public String toString() {
        return "NumberSchema with min=" + minRange
                + "; max=" + maxRange
                + "; isRequired=" + isRequired
                + "; isPositive=" + isPositive;
    }
}
