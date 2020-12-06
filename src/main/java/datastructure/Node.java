package datastructure;

// Trie Node

// Source: https://github.com/usertomlin/efficient-trie
import org.linchimin.simpletrie.*;

import java.util.*;
import java.util.function.Function;


class Node<V> extends TrieNode<V>{

    // Override for supported characters
    static {
       setSupportedChars("1234567890");
    }

    public Node(char c, int level) {
        super(c, level);
    }


//   IMPLEMENTATION DETAILS OF TRIENODE<V> IN COMMENTS FOR EASY LOOKUP
//    public V getValue() {
//        return this.value;
//    }
//
//    public void setValue(V value) {
//        this.value = value;
//    }
//
//    public int getScore() {
//        return this.score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public int getLevel() {
//        return this.level;
//    }
//
//    public boolean isLeaf() {
//        return this.isLeaf;
//    }
//
//    public TrieNode<V> getParent() {
//        return this.parent;
//    }
//
//    public ArrayList<TrieNode<V>> getAncestors() {
//        TrieNode<V> node = this.parent;
//
//        ArrayList result;
//        for(result = new ArrayList(this.level); node != null; node = node.parent) {
//            result.add(node);
//        }
//
//        return result;
//    }
//
//    public String getKey() {
//        char[] keyChars = new char[this.level];
//        int offset = this.level - 1;
//
//        for(TrieNode node = this; node.level > 0; node = node.parent) {
//            keyChars[offset--] = node.c;
//        }
//
//        return new String(keyChars);
//    }
//
//    public int getNumChildren() {
//        return this.numChildren;
//    }
//
//    public List<TrieNode<V>> getNonNullChildren() {
//        ArrayList<TrieNode<V>> result = new ArrayList(this.numChildren);
//
//        for(int i = 0; i < this.numChildren; ++i) {
//            TrieNode<V> child = this.children[this.childrenIndices[i]];
//            result.add(child);
//        }
//
//        return result;
//    }
//
//    public String toString() {
//        return "SuffixTrieNode [isLeaf=" + this.isLeaf + ", score=" + this.score + ", value=" + this.value + ", level=" + this.level + ", key=" + this.getKey() + ", char=" + this.c + "]";
//    }
//
//    public int compareTo(TrieNode<V> o) {
//        return this.score - o.score;
//    }
//
//    public void addChildIndex(int index) {
//        this.childrenIndices[this.numChildren++] = index;
//    }
//
//    public TrieNode<V> getFirstChild() {
//        return this.numChildren == 0 ? null : this.children[this.childrenIndices[0]];
//    }
//
//    public boolean isRoot() {
//        return this.level == 0;
//    }
//
//    public List<TrieNode<V>> getKeyValueNodes() {
//        if (this.KeyValueNodes == null) {
//            ArrayList<TrieNode<V>> lns = new ArrayList();
//            if (this.isLeaf) {
//                lns.add(this);
//            }
//
//            for(int i = 0; i < this.numChildren; ++i) {
//                TrieNode<V> child = this.children[this.childrenIndices[i]];
//                lns.addAll(child.getKeyValueNodes());
//            }
//
//            this.KeyValueNodes = Collections.unmodifiableList(lns);
//        }
//
//        return this.KeyValueNodes;
//    }
//
//    public List<TrieNode<V>> getKeyValueNodes(Function<TrieNode<V>, Boolean> condition) {
//        List<TrieNode<V>> kvNodes = this.getKeyValueNodes();
//        List<TrieNode<V>> result = new ArrayList();
//        Iterator var5 = kvNodes.iterator();
//
//        while(var5.hasNext()) {
//            TrieNode<V> kvNode = (TrieNode)var5.next();
//            if ((Boolean)condition.apply(kvNode)) {
//                result.add(kvNode);
//            }
//        }
//
//        return result;
//    }
//
//    public TrieNode<V> getBestKeyValueNode() {
//        return this.getBestKeyValueNode((a, b) -> {
//            return a.score - b.score;
//        });
//    }
//
//    public TrieNode<V> getBestKeyValueNode(Comparator<TrieNode<V>> comparator) {
//        List<TrieNode<V>> KvNodes = this.getKeyValueNodes();
//        return (TrieNode)Collections.max(KvNodes, comparator);
//    }
//
//    public List<TrieNode<V>> getBestKeyValueNodes(int numTopKeyValueNodes) {
//        return this.getBestKeyValueNodes(numTopKeyValueNodes, (a, b) -> {
//            return a.score - b.score;
//        });
//    }
//
//    public List<TrieNode<V>> getBestKeyValueNodes(int numTopKeyValueNodes, Comparator<TrieNode<V>> comparator) {
//        if (numTopKeyValueNodes <= 0) {
//            throw new IllegalArgumentException("IllegalArgumentException: numTopKeyValueNodes (" + numTopKeyValueNodes + ") should be positive ");
//        } else {
//            List<TrieNode<V>> kvNodes = this.getKeyValueNodes();
//            if (numTopKeyValueNodes == 1) {
//                TrieNode<V> kvNode = (TrieNode)Collections.max(kvNodes, comparator);
//                List<TrieNode<V>> result = new ArrayList();
//                result.add(kvNode);
//                return result;
//            } else {
//                ArrayList<TrieNode<V>> tempNodes = new ArrayList(kvNodes);
//                Collections.sort(tempNodes, comparator.reversed());
//                return tempNodes.subList(0, Math.min(numTopKeyValueNodes, kvNodes.size()));
//            }
//        }
//    }

}
