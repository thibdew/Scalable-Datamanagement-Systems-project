package algorithms;

import tries.TrieIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeapfrogTrieJoin extends Algorithm {

    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();

        // TODO: Create the tries, insert values into the tries and create the TrieIterator

        // For each join variable, there is an array of TrieIterators
        TrieIterator[][] iterators = new TrieIterator[][];

        int depth = 0;
        boolean atEnd = false;

        // Set all the iterators to their first value
        for(int i = 0; i < iterators[depth].length; i++) {
            iterators[depth][i].open();
        }

        // Keep searching until the end of the trie is reached
        while(!atEnd) {
            // Call leapfrogSearch(), this returns true if a match is found and false if none is found
            if(leapfrogSearch(iterators[depth])) {
                // If a match is found and we are not at the bottom level of the trie, descend further
                if(depth < iterators.length - 1) {
                    for(int i = 0; i < iterators[depth].length; i++) {
                        iterators[depth][i].open();
                    }
                    depth++;
                } else { // If we are at the bottom level of the trie, add the tuples with the current join values to the result
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
        // Sort the iterators in ascending order, based on their initial key
        Arrays.sort(iterators, new SortIterator());
        int maxKey = iterators[iterators.length - 1].key();

        // Keep looping until a match is found or until atEnd is true
        while(true) {
            TrieIterator currentIterator = iterators[idx];
            int key = currentIterator.key();
            // If the current key, which is the lowest value of all iterators is equal to the max key, we found a match
            if(key == maxKey) {
                return true;
            } else {
                currentIterator.seek(maxKey);
                if(currentIterator.atEnd()) {
                    return false;
                } else {
                    // The current key becomes the new max key because the seek always returns a key higher or equal than the target
                    maxKey = currentIterator.key();
                    idx = (idx + 1) % iterators.length;
                }
            }
        }
    }

    class SortIterator implements Comparator<TrieIterator> {
        // Sorts the trie in ascending order, based on their current key
        public int compare(TrieIterator a, TrieIterator b) {
            return a.key() - b.key();
        }
    }

}
