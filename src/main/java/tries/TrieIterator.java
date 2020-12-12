package tries;
import org.linchimin.simpletrie.TrieNode;
import tries.util.KeyValue;
import java.util.ArrayList;
import java.util.Iterator;

public class TrieIterator<V> implements AbstractIterator<V> {

    // ------------------------------------------------------------------------------------------
    // INT-STRING CONVERSION TO WORK WITH INTEGER KEYS IN STRING-AS-KEY-BASED TRIE IMPLEMENTATION
    private static String intToString(int i){
        return Integer.toString(i);
    }
    private static int stringToInt(String s){
        return Integer.parseInt(s);
    }
    //-------------------------------------------------------------------------------------------


    private Trie<V> trie;


    private Node<V> currentNode;
    private int currentLevel;

    private int arraySize;
    private boolean atEnd;

    public TrieIterator() {

    }

    // Helper functions
    private void toParent(){
        // can go to parent Node if current node is not equal to root
        if (!currentNode.isRoot()){
            currentNode = (Node<V>) currentNode.getParent();
        }
    }

    private void toChild(int idx){
        // can go to child node at given index

    }


    @Override
    public int key() {
        return stringToInt(currentNode.getKey());
    }

    @Override
    public void next() {


    }

    @Override
    public void seek(int seekKey) {

    }

    @Override
    public boolean atEnd() {
        return false;
    }

    @Override
    public V getValue() {
        return null;
    }


    public void open(){

    }

    public void up(){

    }
}
