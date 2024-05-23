package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired;

    public abstract BaseSchema<T> required();
    /*public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }*/

    public abstract boolean isValid(Object checkValue);
}
