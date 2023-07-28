/*
Junnan Shimizu

Name, date, file, section, project, and course
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordCounter2 {
    private MapSet map;
    private int totalWordCount;

    // constructor, where data_structure is either "bst" or "hashmap".
    // It should create the appropriate data structure and store it in the map field.
    public WordCounter2( String data_structure ){
        if(data_structure.equals("hashmap")){
            map = new Hashmap<String,Integer>(new AscendingString());
        }
        if(data_structure.equals("bst")){
            map = new BSTMap<String,Integer>(new AscendingString());
        }
        totalWordCount = 0;
    }

    // given the filename of a text file, read the text file and
    // return an ArrayList list of all the words in the file.
    public ArrayList<String> readWords(String filename ){
        ArrayList<String> words = new ArrayList<>();
        try {
            FileReader fReader = new FileReader(filename);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                // split line into words. The regular expression can be interpreted
                // as split on anything that is not (^) (a-z or A-Z or 0-9 or ').
                String[] processed = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < processed.length; i++) {
                    String word = processed[i].trim().toLowerCase();
                    if(word.length() != 0){
                        words.add(word);
                    }
                }
                line = bReader.readLine();
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    // given an ArrayList of words, put the words into the map data structure.
    // Return the time taken in ms. Record the time using System.nanoTime().
    public double buildMap( ArrayList<String> words ){
        clearMap();
        double timeOne = System.nanoTime();
        for(String current : words){
            map.put(current, getCount(current) + 1);
            this.totalWordCount++;
        }
        double timeTwo = System.nanoTime();
        return timeTwo - timeOne;
    }

    //clear the map data structure.
    public void clearMap(){
        map.clear();
        totalWordCount = 0;
    }

    //return the total word count from the last time readWords was called.
    public int totalWordCount(){
        return this.totalWordCount;
    }

    //return the unique word count, which should be the size of the map data structure.
    public int uniqueWordCount(){
        return this.map.size();
    }

    // return the number of times the word occurred in the list of words.
    // Query the data structure to get the information. Return 0 if the
    // word does not exist in the data structure.
    public int getCount( String word ){
        if(map.get(word) == null){
            return 0;
        }else{
            return (int) map.get(word);
        }
    }

    // return the frequency of the word in the list of words.
    // Query the data structure to get the word count and then
    // divide by the total number of words to get the frequency.
    // Return 0 if the word does not exist in the data structure.
    public double getFrequency( String word ){
        if(map.get(word) == null){
            return 0;
        }else{
            return (double)(map.get(word)) /  totalWordCount;
        }
    }

    // write a word count file given the current set of words in the data structure.
    // The first line of the file should contain the total number of words.
    // Each subsequent line should contain a word and its frequency.
    public boolean writeWordCount( String filename ) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        FileWriter entry = new FileWriter(file);
        entry.write("Total Word Count: " + totalWordCount + "\n");
        for(Object current : map.entrySet()){
            entry.write(current.toString());
            entry.write("\n");
        }
        entry.close();
        return true;
    }

    // read a word count file given the filename.
    // The function should clear the map and then
    // put all of the words, with their counts, into the map data structure.
    public boolean readWordCount( String filename ) throws IOException {
        this.clearMap();
        FileReader fileReader = new FileReader(filename);
        BufferedReader bReader = new BufferedReader(fileReader);
        String line = bReader.readLine();
        while(line != null){
            String[] keysAndValues = line.split(" ");
            for(String current : keysAndValues){
                System.out.println(current);
            }
            if(keysAndValues.length <= 2){
                map.put(keysAndValues[0], keysAndValues[1]);
                totalWordCount += Integer.valueOf(keysAndValues[1]);
            }
            line = bReader.readLine();
        }
        fileReader.close();
        bReader.close();
        return true;
    }

    public MapSet getMap(){
        return this.map;
    }

    public ArrayList<KeyValuePair<String, Integer>> arrayListReturn(){
        return map.entrySet();
    }

    //testing
    public static void main(String[] args) throws IOException {
        double[] times = new double[5];
        WordCounter2 test = new WordCounter2("hashmap");
        Scanner kboard = new Scanner(System.in);
        System.out.println("File Absolute Path: ");
        String file = kboard.nextLine();

        ArrayList<String> words = test.readWords(file);
        System.out.println("Finished reading words");

        for(int i = 0; i < 5; i++){
            times[i] = test.buildMap(words);
        }

        Arrays.sort(times);
        times[0] = 0;
        times[4] = 0;

        double total = 0;
        for(double current : times){
            System.out.println(current);
            total += current;
        }

        System.out.println("Average Time: " + (total / 3));
        System.out.println("Total Word Count: " + test.totalWordCount());
        System.out.println("Unique Word Count: " + test.uniqueWordCount());
        kboard.close();
    }
}
