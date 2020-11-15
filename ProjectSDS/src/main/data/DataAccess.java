package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataAccess {

    /*Methode to convert a .csv file to usable data
      INPUT: String referring to a file in the resource folder
      OUTPUT: List of array strings containing the data (accessed with data.get(row)[column])
      inspired on: https://stackoverflow.com/a/42170925
     */
    public static List<String[]> readDataset(String filename){
        String path = "src/main/resources/" + filename;
        List<String[]> data = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String entry = reader.readLine();
            while(entry != null){
                data.add(entry.split(","));
                entry = reader.readLine();
            }

        } catch(IOException readingException){
            System.out.println("Couldn't read file");
        }

        return data;
    }

    /*Methode to print a whole dataset
      INPUT: data as a list of array strings
      OUTPUT: void (prints result in console)
     */
    public static void printAll(List<String[]> input){
        for(int i = 0; i < input.size(); i++){
            System.out.println(Arrays.toString(input.get(i)));
        }
    }
}
