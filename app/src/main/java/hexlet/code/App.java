package hexlet.code;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Validator v = new Validator();
        StringSchema schema = v.string();

        var schema2 = schema.required().minLength(5);
        System.out.println("222=" + schema.toString());
        System.out.println("isValidate=" + schema.isValid("123456"));
    }
}
