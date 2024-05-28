package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema stringSchema;
    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        stringSchema = validator.string();
    }
    @Test
    public void testStringSchema() {
        // Пока не вызван метод required(), null и пустая строка считаются валидным
        assertTrue(stringSchema.isValid("")); // true
        assertTrue(stringSchema.isValid(null)); // true

        stringSchema.required();

        assertFalse(stringSchema.isValid(null)); // false
        assertFalse(stringSchema.isValid("")); // false
        assertTrue(stringSchema.isValid("what does the fox say")); // true
        assertTrue(stringSchema.isValid("hexlet")); // true

        assertTrue(stringSchema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(stringSchema.contains("what").isValid("what does the fox say")); // true
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(stringSchema.isValid("what does the fox say")); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema2 = validator.string();
        assertTrue(schema2.minLength(10).minLength(4).isValid("Hexlet")); // true
        assertFalse(schema2.minLength(10).minLength(4).isValid("Hex")); // false
    }

    @Test
    public void requiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid("value"));
        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
    }

    @Test
    public void minLengthStringTest() {
        stringSchema.minLength(10);
        assertTrue(stringSchema.isValid("Hello where!"));
        assertFalse(stringSchema.isValid("value"));
        assertTrue(stringSchema.isValid(null));
    }

}
