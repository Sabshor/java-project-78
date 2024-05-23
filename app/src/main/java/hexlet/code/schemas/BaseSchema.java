package hexlet.code.schemas;

public class BaseSchema {
    private boolean isRequired;

    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public boolean isValid(Object checkValue) {
        return true;
    }
}
