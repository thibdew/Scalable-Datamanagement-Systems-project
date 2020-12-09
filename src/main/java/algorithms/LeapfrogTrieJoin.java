package algorithms;

import tries.TrieIterator;

import java.util.ArrayList;
import java.util.List;

public class LeapfrogTrieJoin extends Algorithm {

    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();

        // TODO: Create the tries and insert values into the tries

        // For each join variable, there is an array of TrieIterators
        TrieIterator[][] iterators = new TrieIterator[][];
        // TODO: Sort the iterators in ascending order of their initial key

        int depth = 0;
        boolean atEnd = false;
        while(!atEnd) {
            for(int i = 0; i < iterators[depth].length; i++) {
                iterators[depth][i].open();
            }

            if(leapfrogSearch(iterators[depth])) {
                if(depth < iterators.length - 1) {
                    depth++;
                } else {
                    result.add(new String[] {iterators[depth][0].key()}); // TODO: change to actual join tuple instead of only 1 attribute
                }
            } else if(depth == 0) {
                atEnd = true;
            } else {
                for(int i = 0; i < iterators[depth].length; i++) {
                    iterators[depth][i].up();
                }
                depth--;
            }
        }

        return result;
    }

    private boolean leapfrogSearch(TrieIterator[] iterators) {
        int idx = 0;
        int maxKey = iterators[iterators.length - 1].key();
        while(true) {
            TrieIterator currentIterator = iterators[idx];
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
