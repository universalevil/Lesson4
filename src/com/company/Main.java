package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, NoCatsFoundInFileException {


        String path = "D:\\Projects\\Lesson4\\src\\com\\company\\cat.txt";
        String logpath =  "D:\\Projects\\Lesson4\\src\\com\\company\\log.txt";



        Map<String, Integer> map = new HashMap<String, Integer>();
        Logger log = Logger.getLogger("TextFullOfCats");
        log.setLevel(Level.INFO);


        //FileHandler fh = null;
        try {

            boolean append = true;
            FileHandler handler = new FileHandler(logpath, append);
            Scanner scanner = new Scanner(new FileReader(path));

            System.out.println("Unsorted:");
            while (scanner.hasNext()) {
                String word = scanner.next().replace(".", "");

                if (map.containsKey(word.toLowerCase()) ) {
                    int temp = map.get(word.toLowerCase()) + 1;
                    map.put(word.toLowerCase(), temp);
                } else {
                    map.put(word.toLowerCase(), 1);
                }
            }

            System.out.println(map);

            Map<String, Integer> result = new LinkedHashMap<>();
            map.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByKey())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
            System.out.println("Sorted:");
            System.out.println(result);


            if(result.containsKey("cat") || result.containsKey("cats")){
                System.out.println("The hashmap contains cats");
            } else {
                throw new NoCatsFoundInFileException("No cats were found!");
            }

        } catch (FileNotFoundException exception) {
            System.out.println("The file " + path + " was not found.");
            log.info(exception.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
class NoCatsFoundInFileException extends Exception
{

    //Constructor that accepts a message
    public NoCatsFoundInFileException(String message)
    {
        super(message);
    }
}









