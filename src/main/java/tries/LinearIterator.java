package tries;

import org.apache.commons.lang3.ArrayUtils;
import tries.util.KeyValue;

import java.util.*;

public class LinearIterator<V> implements AbstractIterator<V>{

    private ArrayList<KeyValue<Integer, V>> keyValues;
    private int currentIndex;
    private final int arraySize;
    private boolean atEnd;


    // Constructor Methods

    // Constructor 1: When only using Keys
    //  ==> KeyValues will be populated with keys and a NO_VALUE entry as their 'dummy' values
    public LinearIterator(int[] keys){
        this.keyValues = new ArrayList<>();
        for(int k : keys){
            this.keyValues.add(new KeyValue<>(k, null));
        }
        this.currentIndex = 0;
        this.arraySize = keyValues.size();
        this.atEnd = false;
    }

    // Constructor 2: When using existing ArrayList<KeyValue>
    public LinearIterator(ArrayList<KeyValue<Integer, V>> keyValuePairs){
        this.keyValues = keyValuePairs;
        this.currentIndex = 0;
        this.arraySize = keyValues.size();
        this.atEnd = false;
    }
    // Constructor 3: When using Key Value pairs
    //  ==> KeyValues will be populated with keys and values (as Strings, read from CSV files)
    // NOTE: NOW SUPPORTED BY INTERFACE ATM!!
    public LinearIterator(int[] keys, V[] values) throws Exception {
        this.keyValues = new ArrayList<>();

        // Only allow equal amounts of keys and values
        if (keys.length != values.length){
            System.out.println("ERROR: More values than keys at LinearIterator construction");
            throw new Exception("LinearIterator constructor: Provided more values than keys, cannot create KeyValue pairs");
        }
        // Else: process all the available values and keys
        else {
            for (int i = 0; i < keys.length; i++){
                int k = keys[i];
                V v = values[i];
                this.keyValues.add(new KeyValue<>(k, v));
            }
        }
        this.currentIndex = 0;
        this.arraySize = keyValues.size();
        this.atEnd = false;
    }


    @Override
    public int key() {
        return keyValues.get(currentIndex).getKey();
    }

    @Override
    public void next() {
        // Increase index if Iterator has more elements
        if (currentIndex < (arraySize - 1)){
            currentIndex++;
        }
        // Set atEnd boolean to True if Iterator now at end
        if (currentIndex == (arraySize - 1)){
            atEnd = true;
        }
        // DEBUGGING Print current position of Iterator
        //System.out.println("Lin. Iterator now at position " + currentIndex + " of " + (arraySize - 1));
    }

    @Override
    public void seek(int seekKey) {
        int currentKey = keyValues.get(currentIndex).getKey();
        // Sought key has to be >= current index key\
        if (seekKey < currentKey){
            System.out.println("LIN ITERATOR ERROR: seekKey " + seekKey + " is not greater or equal than currentKey " + currentKey);
            //throw new Exception("LIN ITERATOR ERROR: seekKey " + seekKey + " is not greater or equal than currentKey " + currentKey);
        } else {
            // Look for the least key that is >= the seekKey, or move to the end of iterator if no such key exists
            //  --> Loop continues to go to next position until either a key has been found or we're at end of Iterator
            while (currentKey < seekKey && (!atEnd)){
                // Position iterator at next (atEnd will be automatically set when at end by using the Next function)
                this.next();
                // set currentKey to key at this index
                currentKey = keyValues.get(currentIndex).getKey();
                // DEBUGGING //
                //System.out.println("LIN ITER SEEK: Current Key: " + currentKey + " ; SeekKey " + seekKey);
            }
        }
    }

    @Override
    public boolean atEnd() {
        return atEnd;
    }

    // Gets value of current index
    @Override
    public V getValue(){
        return keyValues.get(currentIndex).getValue();
    }

    // Utility method for resetting Iterator interface to idx 0
    public void resetIterator(){
        currentIndex = 0;
    }
}
