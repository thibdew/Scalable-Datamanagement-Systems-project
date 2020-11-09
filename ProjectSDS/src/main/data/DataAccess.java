package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

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

    public static void main(String[] args) {
        List<String[]> data = readDataset("aka_name.csv");
        System.out.println(data.get(1000)[0]);
    }
}
