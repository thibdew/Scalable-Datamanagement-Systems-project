package algorithms;

import datastructure.Trie;

import java.util.ArrayList;
import java.util.List;

public class LeapfrogTrieJoin extends Algorithm{
    Trie<Integer>[] tries;

    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();

        // TODO: Create the tries and insert values into the tries

        // TODO: Sort the tries in an ascending order

        while(leapfrogSearch()) {
            String[] entry = new String[tries.length];
            for(int i=0; i < tries.length; i++) {
                entry[i] = String.valueOf(tries[i].key());
            }
            result.add(entry);
            tries[0].next();
        }

        return result;
    }

    private boolean leapfrogSearch() {
        int idx = 0;
        int maxKey = tries[tries.length - 1].key();
        while(true) {
            Trie currentTrie = tries[idx];
            int key = currentTrie.key();
            if(key == maxKey) {
                return true;
            } else {
                currentTrie.seek(maxKey);
                if(currentTrie.atEnd()) {
                    return false;
                } else {
                    maxKey = currentTrie.key();
                    idx = (idx + 1) % tries.length;
                }
            }
        }
    }

}
