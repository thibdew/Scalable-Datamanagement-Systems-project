package tries.util;

import java.util.ArrayList;

public class Converter {

    // converts String attributes to the sum of their ASCII char values]
    //  --> Utility to use String typed attributeds in the TrieJoin algorithm
    public static int stringKeyToIntKey(String key){
        char[] charArray = key.toCharArray();
        ArrayList<Integer> intArray = new ArrayList<>();

        for (char c : charArray) {
            intArray.add((int) c);
            //System.out.println("Converting " + c + " to ASCII value: " + (int) c);
        }
        int keyAsInt = intArray.stream().reduce(0, (accumulator, el) -> accumulator + el);
        //System.out.println("Summed value: " + keyAsInt);
        return keyAsInt;
    }

    public static int[] stringKeysToIntKeys(String[] keyArray) {
        int[] intKeyArray = new int[keyArray.length];
        for (int i = 0; i < keyArray.length; i++) {
            intKeyArray[i] = stringKeyToIntKey(keyArray[i]);
        }
        return intKeyArray;
    }

}
