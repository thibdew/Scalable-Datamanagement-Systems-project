package algorithms;

import tries.LinearIterator;
import tries.Trie;

import java.util.ArrayList;
import java.util.List;

public class LeapfrogTrieJoin extends Algorithm{
    LinearIterator[] iterators;

    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();

        // TODO: Create the tries and insert values into the tries

        // TODO: Sort the iterators in ascending order of their initial key

        while(leapfrogSearch()) {
            String[] entry = new String[iterators.length];
            for(int i = 0; i < iterators.length; i++) {
                entry[i] = String.valueOf(iterators[i].key());
            }
            result.add(entry);
            iterators[0].next();
        }

        return result;
    }

    private boolean leapfrogSearch() {
        int idx = 0;
        int maxKey = iterators[iterators.length - 1].key();
        while(true) {
            LinearIterator currentIterator = iterators[idx];
            int key = currentIterator.key();
            if(key == maxKey) {
                return true;
            } else {
                currentIterator.seek(maxKey);
                if(currentIterator.atEnd()) {
                    return false;
                } else {
                    maxKey = currentIterator.key();
                    idx = (idx + 1) % iterators.length;
                }
            }
        }
    }

}
