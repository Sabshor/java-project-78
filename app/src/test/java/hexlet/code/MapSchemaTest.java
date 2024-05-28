package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {
    private MapSchema mapSchema;
    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        mapSchema = validator.map();
    }

    @Test
    public void testMapSchema() {
        assertTrue(mapSchema.isValid(null)); // true

        mapSchema.required();

        assertFalse(mapSchema.isValid(null)); // false
        assertTrue(mapSchema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data)); // true

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data)); // true

        var schema2 = validator.map();
        schema2.required();
        var data2 = new HashMap<NumberSchema, StringSchema>();
        data2.put(new NumberSchema(), new StringSchema());
        assertTrue(schema2.isValid(data2));  // true
        schema2.sizeof(1);
        assertTrue(schema2.isValid(data2));  // true
    }

    @Test
    public void testShapeMapSchema() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        //Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3)); // false

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("firstName", validator.string().required());
        schemas2.put("lastName", validator.string().required().minLength(2));
        schemas2.put("age", validator.number());
        mapSchema.shape(schemas2);
        assertTrue(mapSchema.isValid(human1)); // true

        Map<String, BaseSchema> schemas3 = new HashMap<>();
        schemas3.put("firstName", validator.string().required());
        schemas3.put("lastName", validator.string().required().minLength(2));
        schemas3.put("age", validator.number().required().range(10, 20));
        mapSchema.shape(schemas3);
        assertFalse(mapSchema.isValid(human1)); // false

        human1.put("age", "25");
        assertFalse(mapSchema.isValid(human1));

        Map<String, BaseSchema> schemas4 = new HashMap<>();
        schemas4.put("value", validator.string().minLength(4));
        schemas4.put("num", validator.number().required().positive().range(10, 20));
        mapSchema.shape(schemas4);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("value", "Test");
        human4.put("num", -1);
        assertFalse(mapSchema.isValid(human4));

        Validator v2 = new Validator();
        MapSchema schema5 = v2.map();
        schema5.required().sizeof(3);

        assertFalse(schema5.isValid(null));
        assertFalse(schema5.isValid(new HashMap<>()));
    }

    @Test
    public void requiredTest() {
        assertTrue(mapSchema.isValid(null));
        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void sizeTest() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
        assertTrue(mapSchema.isValid(null));
    }
}
