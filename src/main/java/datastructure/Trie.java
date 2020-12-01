package datastructure;


// Trie datastructure
//  Base implementation
//  Source: https://github.com/eugenp/tutorials/blob/master/data-structures/src/main/java/com/baeldung/trie/TrieNode.java

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


class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    boolean delete(String word) {
        return delete(root, word, 0);
    }

    boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }
}
