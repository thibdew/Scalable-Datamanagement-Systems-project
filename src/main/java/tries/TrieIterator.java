package tries;

public class TrieIterator<V> implements AbstractIterator<V> {

    private final Trie<V> trie;
    private Node<V> currentNode;
    private int currentLevel;
    private boolean atEnd;

    public TrieIterator(int[][] tupleArray){
        int depth = tupleArray[0].length;
        this.trie = new Trie<>(new Node<>(-1, null, 0), depth); // create root
        for (int[] currentTuple : tupleArray) {
            this.trie.insert(currentTuple, null);
        }
        this.currentNode = this.trie.getRoot();
    }
    public TrieIterator(int[][] tupleArray, V[] valueArray) {
        int depth = tupleArray[0].length;
        this.trie = new Trie<>(new Node<>(-1, null, 0), depth); // create root
        for (int i = 0; i < tupleArray.length; i++){
            int[] currentTuple = tupleArray[i];
            V currentValue = valueArray[i];
            this.trie.insert(currentTuple, currentValue);
        }
        this.currentNode = this.trie.getRoot();
    }

    // Utility
    private void updateLevel(){
        currentLevel = currentNode.getLevel();
    }

    @Override
    public int key() {
        return currentNode.getKey();
    }

    @Override
    public void next() {
        if (currentNode.parentHasNextChild()) {
            currentNode = currentNode.getNextChildOfParent();
        }

        // If the now updated node has no more nexts, set atEnd to true
        if (!currentNode.parentHasNextChild()) {
            atEnd = true;
        }
    }

    @Override
    public void seek(int seekKey) {
        int currentKey = currentNode.getKey();
        // Sought key has to be >= current index key
        if (seekKey < currentKey){
            System.out.println("TRIE ITERATOR ERROR: seekKey " + seekKey + " is not greater or equal than currentKey " + currentKey);
        } else {
            // Look for the least key that is >= the seekKey, or move to the end of iterator if no such key exists
            //  --> Loop continues to go to next position until either a key has been found or we're at end of Iterator
            while (currentKey < seekKey && (!atEnd)) {
                // Position iterator at next (atEnd will be automatically set when at end by using the Next function)
                this.next();
                // set currentKey to key at this index
                currentKey = currentNode.getKey();
                // DEBUGGING //
                //System.out.println("TRIE ITER SEEK: Current Key: " + currentKey + " ; SeekKey " + seekKey);
                //////////////////
            }
        }
    }

    @Override
    public boolean atEnd() {
        return atEnd;
    }

    @Override
    public V getValue() {
        return currentNode.getValue();
    }

    public void open(){
        currentNode = currentNode.getFirstChild();
        updateLevel();

    }

    public void up(){
        // can go to parent Node if current node is not equal to root
        if (!currentNode.isRoot()){
            currentNode = currentNode.getParent();
            updateLevel();
        }
    }


    // Ease of use extensions of interface

    /// Get the current Trie depth level at which the Trie iterator is positioned
    /// -> 0 means root, up till TRIE-DEPTH for the deepest level
    public int getCurrentLevel(){
        return currentLevel;
    }

    /// is TrieIterator positioned at the rootnode
    public boolean atRoot(){
        return currentNode.isRoot();
    }

    // is TrieIterator positioned at any leaf node (lowest level)
    public boolean atLeaves(){
        return currentNode.isLeaf();
    }

    // UtilityMethod for resetting Iterator interface to rootnode
    public void resetIterator(){
        currentNode = trie.getRoot();
        currentLevel = 0;
    }
}
