package hexlet.code;

import hexlet.code.schemes.MapSchema;
import hexlet.code.schemes.NumberSchema;
import hexlet.code.schemes.StringSchema;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }

}
