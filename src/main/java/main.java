import algorithms.Algorithm;
import data.DataAccess;

import java.util.ArrayList;
import java.util.Date;
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
            System.out.println("Completed run: " + i);
            if(i>=warmUps){
                runtimes.add(System.nanoTime()-before);
            }
        }
        return runtimes;
    }

    public static void main(String[] args) {
        //Nested loop join example
//        List<String[]> data1 = DataAccess.readDataset("IMDb movies.csv");
//        List<String[]> data2 = DataAccess.readDataset("IMDb ratings.csv");
//        algorithms.NestedLoopJoin join = new algorithms.NestedLoopJoin(data1, data2, 0,0);
//        DataAccess.printAll(join.computeJoin());
    }
}
