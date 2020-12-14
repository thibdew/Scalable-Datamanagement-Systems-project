package tries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Simple Test suite for the TrieIterator class
public class TrieIteratorTest {


    // Create TrieIterator
    // -> trie example from paper
    private final int[][] tuples = {{1, 3, 4},
                                    {1, 3, 5},
                                    {1, 4, 6},
                                    {1, 4, 8},
                                    {1, 4, 9},
                                    {1, 5, 2},
                                    {3, 5, 2}};

    private final TrieIterator<String> trieIt = new TrieIterator<>(tuples);
    @BeforeEach
    void initLinearIterator(){
        trieIt.resetIterator();
    }

    @Test
    void open() {
        // Open should increase the value of the currentLevel to a deeper level
        assertEquals(0, trieIt.getCurrentLevel());
        assertTrue(trieIt.atRoot());
        trieIt.open();
        assertEquals(1, trieIt.getCurrentLevel());


    }

    @Test
    void up() {
        // Up should decrease the value of currentLevel, but should not be able to go under 0 (above root)
        assertEquals(0, trieIt.getCurrentLevel());
        trieIt.up();
        assertEquals(0, trieIt.getCurrentLevel());
        trieIt.open();
        trieIt.open();
        trieIt.up();
        assertEquals(1, trieIt.getCurrentLevel());
    }

    @Test
    void key(){
        //Key should return the key at current position
        trieIt.open();
        assertEquals(1, trieIt.key());
        trieIt.open();
        trieIt.open();
        assertEquals(4, trieIt.key());
    }

    @Test
    void next(){
        //Next should move Iterator to next position
        trieIt.open();
        trieIt.open();
        trieIt.open();
        trieIt.next();
        assertEquals(5, trieIt.key());

        trieIt.next();
        assertEquals(5, trieIt.key());

    }

    @Test
    void atEnd(){
        trieIt.open();
        trieIt.open();
        trieIt.open();
        trieIt.next();
        assertTrue(trieIt.atEnd());

        trieIt.up();
        trieIt.next();
        trieIt.open();
        trieIt.next();
        trieIt.next();
        trieIt.next();

        assertTrue(trieIt.atEnd());
    }

    @Test
    void seek(){
        // Seek should put iterator at idx of least key greater or equal than seekKey,
        //  or at End if no iterator

        // (we perform this test on the 1, 4 branch of the Trie)
        trieIt.open();
        trieIt.open();
        trieIt.next();
        trieIt.open();

        // seekKey is smaller than expected least key
        trieIt.seek(7);
        assertEquals(8, trieIt.key());

        trieIt.resetIterator();
        trieIt.open();
        trieIt.open();
        trieIt.next();
        trieIt.open();


        // seekKey equal to expected least key
        trieIt.seek(6);
        assertEquals(6, trieIt.key());

        trieIt.resetIterator();
        trieIt.open();
        trieIt.open();
        trieIt.next();
        trieIt.open();


        // seekKey smaller than currentKey --> Iterator should not have moved
        trieIt.next(); // now at 8
        trieIt.seek(6);
        assertEquals(8, trieIt.key());

        trieIt.resetIterator();
        trieIt.open();
        trieIt.open();
        trieIt.next();
        trieIt.open();

        // seekKey not found --> Iterator should be at End
        trieIt.seek(11);
        assertTrue(trieIt.atEnd());
    }

}
