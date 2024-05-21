package hexlet.code;

import hexlet.code.schemes.NumberSchema;
import hexlet.code.schemes.StringSchema;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Validator v = new Validator();
        StringSchema schema = v.string();

        System.out.println("222=" + schema.toString());
        System.out.println("isValidate=" + schema.isValid("123456"));

        System.out.println();
        NumberSchema schemaN = v.number();
        System.out.println("n=" + schemaN.isValid(5));
    }
}
