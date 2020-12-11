package algorithms;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashJoin extends Algorithm {
    List<String[]> dataset1;
    List<String[]> dataset2;
    Integer attribute1;
    Integer attribute2;

    /*Constructor
      INPUT: 2 datasets and their respective attribute index to join on
     */
    public HashJoin(List<String[]>inputData1, List<String[]>inputData2, Integer inputAttribute1, Integer inputAttribute2){
        dataset1 = inputData1;
        dataset2 = inputData2;
        attribute1 = inputAttribute1;
        attribute2 = inputAttribute2;
    }

    /*Methode to compute a nested loop join on the given data
      INPUT: /
      OUTPUT: List of string arrays containing the dataset with a joined attribute
     */
    @Override
    public List<String[]> computeJoin() {
        List<String[]> result = new ArrayList<>();
        //            List<String[][]> result = new ArrayList<>();
        Map<String, List<String[]>> map = new HashMap<>();

        for (String[] record : dataset1) {
            List<String[]> v = map.getOrDefault(record[attribute1], new ArrayList<>());
            v.add(record);
            map.put(record[attribute1], v);
        }

        for (String[] record : dataset2) {
            List<String[]> lst = map.get(record[attribute2]);
            if (lst != null) {
                for (String[] r : lst) {
                    String[] entry = ArrayUtils.addAll(r, ArrayUtils.remove(record, attribute2));
                    result.add(entry);
                }
            }
        }

        return result;
    }
}

// Code partly based on https://rosettacode.org/wiki/Hash_join#Java
