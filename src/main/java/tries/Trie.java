package tries;

//TODO
// 1. Verify which format the words of the Trie will be (internally)
//  => only integers (e.g. a words like: 1, 2, 3)
//  => or the format of the paper: (e.g. A(1, 2, 3), B(1, 2, 3))
//  => Adapt methods like insert and search to support these characters
// 2. Look into the Iterator interface
//  => Support for open() and up()
//  => Need specific pointer back to parent node in the TrieNode class?,
//  => or keep track of parent during traversal current using a 'word array' containing the current prefix, with references to the nodes
// 3. Check the need for a generic Iterator interface
//  => As paper supports both linear iterators for e.g. A(x), B(y), as well as n-ary ones using tries for e.g. C(u,v,w).
//  => Written on top of current interface, or use a check to verify the type of iterator before traversing?

// https://github.com/usertomlin/efficient-trie
import org.linchimin.simpletrie.*;

import java.util.List;

public class Trie<V> extends PrefixTrie<V> {

    // Constructor methods:
    // Either using a List<String> of separate elements, or using a string array of words with values
    public Trie(List<String> elements, List<V> values) {
        super(elements, values);
    }

    public Trie(List<String> elements, List<V> values, int[] scores) {
        super(elements, values, scores);
    }

    public Trie(String[] words, V[] values) {
        super(words, values);
    }

    public Trie(String[] words, V[] values, int[] scores) {
        super(words, values, scores);
    }


}
