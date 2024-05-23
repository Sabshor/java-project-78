package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    @Test
    public void testStringSchema() {
        var v = new Validator();
        var schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
        assertFalse(schema1.minLength(10).minLength(4).isValid("Hex")); // false
    }

    @Test
    public void testNumberSchema() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(5)); // true

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true

        // Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }

    @Test
    public void testMapSchema() {
        var v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true

        var schema2 = v.map();
        schema2.required();
        var data2 = new HashMap<NumberSchema, StringSchema>();
        data2.put(new NumberSchema(), new StringSchema());
        assertTrue(schema2.isValid(data2));  // true
        schema.sizeof(1);
        assertTrue(schema2.isValid(data2));  // true
    }

    @Test
    public void testShapeMapSchema() {
        var v = new Validator();
        var schema = v.map();

        //Map<String, BaseSchema<String>> schemas = new HashMap<>();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("firstName", v.string().required());
        schemas2.put("lastName", v.string().required().minLength(2));
        schemas2.put("age", v.number());
        schema.shape(schemas2);
        assertTrue(schema.isValid(human1)); // true

        Map<String, BaseSchema> schemas3 = new HashMap<>();
        schemas3.put("firstName", v.string().required());
        schemas3.put("lastName", v.string().required().minLength(2));
        schemas3.put("age", v.number().required().range(10, 20));
        schema.shape(schemas3);
        assertFalse(schema.isValid(human1)); // false

        human1.put("age", "25");
        assertFalse(schema.isValid(human1));
    }
}
