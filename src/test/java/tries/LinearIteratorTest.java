package tries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Simple Test suite for the LinearIterator class
public class LinearIteratorTest {

    // Create LinearIterator
    private final int[] keys = {1, 2, 3, 4, 6, 7, 9};
    private final LinearIterator linIt = new LinearIterator(keys);


    @BeforeEach
    void initLinearIterator(){
        linIt.resetIterator();
    }

    @Test
    void key(){
        //Key should return the key at current position
        assertEquals(1, linIt.key());
    }

    @Test
    void next(){
        //Next should move Iterator to next position
        // --> Key at idx 1 should equal value 2
        linIt.next();
        assertEquals(2, linIt.key());
    }

    @Test
    void atEnd(){
        linIt.next();
        linIt.next();
        linIt.next();
        linIt.next();
        linIt.next();
        linIt.next();
        assertTrue(linIt.atEnd());
    }

    @Test
    void seek(){
        // Seek should put iterator at idx of least key greater or equal than seekKey,
        //  or at End if no iterator

        // seekKey is smaller than expected least key
        linIt.seek(5);
        assertEquals(6, linIt.key());
        linIt.resetIterator();

        // seekKey equal to expected least key
        linIt.seek(4);
        assertEquals(4, linIt.key());
        linIt.resetIterator();

        // seekKey smaller than currentKey --> Iterator should not have moved
        linIt.next();
        linIt.next();
        linIt.seek(2);
        assertEquals(3, linIt.key());
        linIt.resetIterator();

        // seekKey not found --> Iterator should be at End
        linIt.seek(11);
        assertTrue(linIt.atEnd());
    }
}
