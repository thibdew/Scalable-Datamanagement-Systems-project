package tries;

// Trie
// A custom Trie implementation specifically for the LeapfrogTrieJoin Algorithm
// The design choices for Trie and Trienode have been made to maximise Trie performance (at a greater memory cost)
// Implementation inspired by: https://www.baeldung.com/trie-java and https://github.com/usertomlin/efficient-trie
// Coded by: Brent. W

public class Trie<V>{
    private Node<V> root;

    private Node<V> currentIteratorNode;  // for built-in iterator functionality
    // Determines what the depth is of the Trie
    //  -> specifies at which depth the leaf nodes are situated (ie, the end-of-word trienodes that contain pointers to possible values)
    private int depth;

    public Trie(Node<V> root, int depth) {
        this.root = root;
        this.depth = depth;
    }

    public Node<V> getRoot() {
        return root;
    }


    // Insert
    // -> Insert a word (being an array of integers) into the Trie structure
    // -> takes a int[] containing the keys of the given relation (i.e. (1, 3, 4) becomes: [1, 3, 4])
    protected boolean insert(int[] keyArray, V value) {
        Node<V> currentNode = this.root;

        if (keyArray.length != depth){  // If number of relations are not equal to the depth of the trie (equalling the number of supposed relations), the relation keyArray cannot be added (would not be compatible with the other entries)
            return false;
        } else {
            int currLevel = 0;
            for (int key : keyArray){
                ++currLevel;
                V val = (currLevel == depth) ? value : null; // If we are at deepest level, assign the given value to the leafNode
                int lvl = currLevel;
                currentNode = currentNode.addChild(new Node<>(key, val, lvl)); // adds the child if not yet present in children mapping and sets the parent to current node, then returns the new childnode
            }
            currentNode.setLeaf(true); // mark the leafNode as leaf
            return true;
        }
    }

    // Insert (2): for inserting words without adding a value to it
    protected boolean insert(int[] keyArray){
        return insert(keyArray, null);
    }

    // Search
    // TODO: unfinished, not essential for TrieIterator Interface

    // Delete
    // TODO: unfinished, not essential for TrieIterator Interface





}



