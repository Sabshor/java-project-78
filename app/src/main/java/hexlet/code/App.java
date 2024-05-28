package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println();
        System.out.println();

        MapSchema schemaM = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        //Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schemaM.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        System.out.println(schemaM.isValid(human1)); // true

        //Map<String, BaseSchema<?>> schemas2 = new HashMap<>();
        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("firstName", v.string().required());
        schemas2.put("lastName", v.string().required().minLength(2));
        schemas2.put("age", v.number().required().positive());
        schemaM.shape(schemas2);
        System.out.println("должно быть false = " + schemaM.isValid(human1)); // false
        Map<String, BaseSchema> schemas3 = new HashMap<>();
        schemas3.put("firstName", v.string().required());
        schemas3.put("lastName", v.string().required().minLength(2));
        schemas3.put("age", v.number().required().range(10, 20));
        schemaM.shape(schemas3);
        human1.put("age", "15");
        System.out.println("должно быть true = " + schemaM.isValid(human1));
    }
}
