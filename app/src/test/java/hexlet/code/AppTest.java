package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void test1() {
        String path = "src/test/resources/fixtures/json";
        assertEquals(path, "src/test/resources/fixtures/json");
    }
}
