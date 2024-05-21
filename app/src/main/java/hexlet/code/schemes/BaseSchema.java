package hexlet.code.schemes;

public abstract class BaseSchema<T> {
    public abstract boolean isValid(Object checkValue);
}
