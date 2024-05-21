package hexlet.code.schemes;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private boolean isRequired;
    private boolean isPositive;
    private Integer minRange;
    private Integer maxRange;

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

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
        Integer value = (Integer) checkValue;
        if (value == null) {
            if (isRequired) {
                isValidate = false;
            }
            return isValidate;
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
