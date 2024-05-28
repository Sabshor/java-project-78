package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private NumberSchema numberSchema;
    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        numberSchema = validator.number();
    }
    @Test
    public void testNumberSchema() {
        assertTrue(numberSchema.isValid(5)); // true

        // Пока не вызван метод required(), null считается валидным
        assertTrue(numberSchema.isValid(null)); // true
        assertTrue(numberSchema.positive().isValid(null)); // true

        numberSchema.required();

        assertFalse(numberSchema.isValid(null)); // false
        assertTrue(numberSchema.isValid(10)); // true

        // Потому что ранее мы вызвали метод positive()
        assertFalse(numberSchema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(numberSchema.isValid(0)); // false

        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5)); // true
        assertTrue(numberSchema.isValid(10)); // true
        assertFalse(numberSchema.isValid(4)); // false
        assertFalse(numberSchema.isValid(11)); // false

        var schema2 = validator.number().required().range(10, 20);
        assertTrue(schema2.isValid(15)); // true

        var schema3 = validator.number();
        schema3.range(5, 10);
        assertTrue(schema3.isValid(5));
        assertTrue(schema3.isValid(null));
        assertTrue(schema3.isValid(10));
        schema3.range(10, 5);
        assertFalse(schema3.isValid(6));
        schema3.range(-8, -3);
        assertTrue(schema3.isValid(-6));
    }

    @Test
    public void requiredTest() {
        assertTrue(numberSchema.isValid(null));
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void positiveTest() {
        assertTrue(numberSchema.isValid(0));
        numberSchema.positive();
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(-10));
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(0));
    }

    @Test
    public void rangeTest() {
        numberSchema.range(5, 10);
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(10));
        numberSchema.range(10, 5);
        assertFalse(numberSchema.isValid(6));
        numberSchema.range(-8, -3);
        assertTrue(numberSchema.isValid(-6));
    }
}
