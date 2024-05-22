package hexlet.code.schemes;

public abstract class BaseSchema<T> {
    private boolean isRequired;

    public T required() {
        this.isRequired = true;
        return (T) this;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    //public abstract T required2();
    public abstract boolean isValid(Object checkValue);
}
