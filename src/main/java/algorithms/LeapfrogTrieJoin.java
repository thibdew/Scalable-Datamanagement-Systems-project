package algorithms;

import tries.TrieIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeapfrogTrieJoin extends Algorithm {

    List<String[]> inputData1;
    List<String[]> inputData2;
    int inputAttribute1;
    int inputAttribute2;

    int[] attribute1;
    int[] attribute2;

    public LeapfrogTrieJoin(List<String[]> inputData1, List<String[]> inputData2, int inputAttribute1, int inputAttribute2) {
        this.inputData1 = inputData1;
        this.inputData2 = inputData2;
        this.inputData1.sort(new SortData());
        this.inputData2.sort(new SortData());
        this.inputAttribute1 = inputAttribute1;
        this.inputAttribute2 = inputAttribute2;
        this.attribute1 = new int[inputData1.size()];
        this.attribute2 = new int[inputData2.size()];
        for(int i = 1; i < inputData1.size(); i++) {
            attribute1[i] = Integer.parseInt(inputData1.get(i)[inputAttribute1]);
        }
        for(int i = 1; i < inputData2.size(); i++) {
            attribute2[i] = Integer.parseInt(inputData2.get(i)[inputAttribute2]);
        }
    }

    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();

        TrieIterator firstIterator = new TrieIterator(new int[][]{attribute1});
        TrieIterator secondIterator = new TrieIterator(new int[][]{attribute2});

        // For each join variable, there is an array of TrieIterators
        TrieIterator[][] iterators = new TrieIterator[][]{new TrieIterator[]{firstIterator, secondIterator}};

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
                    result.add(new String[] { String.valueOf(iterators[depth][0].key())}); // TODO: change to actual join tuple instead of only 1 attribute
                    // Currently all tries are on the same key, advance one for the next iteration of leapfrogSearch
                    iterators[depth][0].next();
                    if(iterators[depth][0].atEnd()) {
                        if(depth == 0) {
                            atEnd = true;
                        } else {
                            for(int i = 0; i < iterators[depth].length; i++) {
                                iterators[depth][i].up();
                            }
                            depth--;
                            // Currently all tries are on the same key, advance one for the next iteration of leapfrogSearch
                            iterators[depth][0].next();
                        }
                    }
                }
            } else if(depth == 0) {
                atEnd = true;
            } else {
                for(int i = 0; i < iterators[depth].length; i++) {
                    iterators[depth][i].up();
                }
                depth--;
                // Currently all tries are on the same key, advance one for the next iteration of leapfrogSearch
                iterators[depth][0].next();
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

    static class SortData implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            return o1[0].compareTo(o2[0]);
        }
    }
}
