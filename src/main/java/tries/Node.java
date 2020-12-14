package tries;

// TrieNode
// A custom TrieNode implementation specifically for the LeapfrogTrieJoin Algorithm
//  --> Uses numerical keys as node 'keys', with the leaves pointing to a DBMS entry / the record
//
// The design choices for Trie and Trienode have been made to maximise Trie performance (at a greater memory cost)
// Implementation inspired by: https://www.baeldung.com/trie-java and https://github.com/usertomlin/efficient-trie
// Coded by: Brent. W

import java.util.*;

public class Node<V> {

    // References
    private Node<V> parent;
    private TreeMap<Integer, Node<V>> children; // TreeMap as best of both worlds: Hashmap and Sorted Tree properties for fast contains (hashmap) and next (List)

    // Data variables
    //private KeyValue<Integer, V> keyValue;
    private final int key;  //  The key a TrieNode has is equal to index it has in the array of children of its parent node.
    private V value; // Value of node (leaf nodes only) -> allows the algorithm to get the corresponding tuples when found a matching tuple

    // Helper variables
    private int numberOfChildren; // Number of children -- helper variable
    private boolean isLeaf;  //whether or not the current node is the leaf (i.e. the deepest level of the trie at the current branch)
    private final int level;


    // Constructor
    public Node(int key, V value, int level) {
        // initialize Data variable
        //this.keyValue = new KeyValue<>(key, value);
        this.key = key;
        this.value = value;
        this.children = new TreeMap<>();
        // set helper variables
        this.isLeaf = true; // By default, on creation, a TrieNode is the end of a word (since at creation, it is the deepest current level)
        this.level = level;
        this.parent = null;
    }


    // Getting the key (ease of use addition to ask the value instead of having to derive it using the index)
    public int getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }


    // Parent
    public Node<V> getParent() {
        return parent;
    }

    public void setParent(Node<V> parent) {
        this.parent = parent;
    }

    // ChildNodes access
    /// Adding a Child
    /// -> Adds the child if not present yet, and returns the value now present at the mapping (either the already present value or the newly added one)
    ///   (the return value is necessary for the Trie class 'insert' loop)
    public Node<V> addChild(Node<V> newChild) {
        newChild.setParent(this); // set its parent to current TreeNode
        Node<V> child = children.computeIfAbsent(newChild.key, k -> newChild); // Only add when not already present at that location
        numberOfChildren = children.size(); // update childrencount
        return child; // for insert iteration purposes
    }

    /// Raw access to children
    public TreeMap<Integer, Node<V>> getChildren() {
        return children;
    }


    // Getting and setting -- Helper variables
    /// Root status
    public boolean isRoot() {
        return level == 0;
    }

    /// Leaf status
    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /// Children data
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    //// Utility for incrementing child count during creation of Trie
    public void updateChildrenCout() {
        numberOfChildren = children.size();
    }

    /// Level data
    public int getLevel() {
        return level;
    }

    /// TrieIterator next() utilities
    private boolean parentContainsKeyAbove(int key) {
        return this.parent.children.higherKey(key) != null;
    }

    private boolean parentContainsKeyAboveOrEqual(int key) {
        return parentContainsKeyAbove(key - 1);
    }

    private Node<V> getParentValueAbove(int key) {
        return this.parent.children.higherEntry(key).getValue();
    }

    private Node<V> getParentValueAboveOrEqual(int key) {
        return getParentValueAbove(key - 1);
    }

    /// Next utilities
    public boolean parentHasNextChild() {
        return parentContainsKeyAbove(this.key);
    }

    public Node<V> getNextChildOfParent() {
        return getParentValueAbove(this.key);
    }

    /// Open and Up Utilities
    public Node<V> getFirstChild() {
        return children.firstEntry().getValue();
    }
}