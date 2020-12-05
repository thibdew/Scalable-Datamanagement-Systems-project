package algorithms;

import org.apache.commons.lang3.ArrayUtils;
import java.util.ArrayList;
import java.util.List;


public class NestedLoopJoin extends Algorithm{
    List<String[]> dataset1;
    List<String[]> dataset2;
    Integer attribute1;
    Integer attribute2;

    /*Constructor
      INPUT: 2 datasets and their respective attribute index to join on
     */
    public NestedLoopJoin(List<String[]>inputData1, List<String[]>inputData2, Integer inputAttribute1, Integer inputAttribute2){
        dataset1 = inputData1;
        dataset2 = inputData2;
        attribute1 = inputAttribute1;
        attribute2 = inputAttribute2;
    }

    /*Methode to compute a nested loop join on the given data
      INPUT: /
      OUTPUT: List of string arrays containing the dataset with a joined attribute
     */
    public List<String[]> computeJoin(){
        List<String[]> result = new ArrayList<>();

        for(int i = 0; i < dataset1.size(); i++){
            for(int j = 0; j < dataset2.size(); j++){
                if(dataset1.get(i)[attribute1].equals(dataset2.get(j)[attribute2])){
                    //System.out.println("matched");
                    String[] entry = ArrayUtils.addAll(dataset1.get(i), ArrayUtils.remove(dataset2.get(j), attribute2));
                    result.add(entry);
                }
            }
        }

        return result;
    }
}
