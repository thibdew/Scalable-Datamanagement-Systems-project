import algorithms.Algorithm;
import algorithms.NestedLoopJoin;
import data.DataAccess;

import java.util.ArrayList;
import java.util.List;

public class main {

    /*Methode to measure the performance of the algorithm over n runs
      INPUT: algorithm object, amount of runs that need to be measured, amount of warm-up runs
      OUTPUT: List of runtimes for each run
    */
    public static List<Double> performanceTest(Algorithm algorithm, Integer runs, Integer warmUps){
        List<Double> runtimes = new ArrayList<>(runs);

        for(int i = 0; i < runs + warmUps; i++){
            System.gc();
            Double before = (double)System.nanoTime(); //time is measured in ns
            algorithm.computeJoin();
            if(i>=warmUps){
                runtimes.add(System.nanoTime()-before);
            }
        }
        return runtimes;
    }

    public static void main(String[] args) {
        /*
        List<String[]> data1 = DataAccess.readDataset("test.csv");
        List<String[]> data2 = DataAccess.readDataset("test2.csv");
        NestedLoopJoin join = new NestedLoopJoin(data1, data2, 1,1);
        DataAccess.printAll(join.computeJoin());
        System.out.println(performanceTest(join, 3,2));
        */
    }
}
