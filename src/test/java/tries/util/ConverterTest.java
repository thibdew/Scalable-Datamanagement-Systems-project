package tries.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConverterTest {

    int i = 1;

    @Test
    void keyAsString(){
        assertEquals(303, Converter.stringKeyToIntKey("def"));  // d = 100, e = 101, f = 102
        assertEquals(1072, Converter.stringKeyToIntKey("ABes>1?qu34vd")); // a test to check Caps and alternate characters
    }

}
