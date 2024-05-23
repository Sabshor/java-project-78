package hexlet.code.schemas;

public class BaseSchema<T> {
    private boolean isRequired;

    public BaseSchema<T> required() {
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
