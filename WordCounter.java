/**
 * File: WordFrequency.java
 * Author: Junnan Shimizu
 * Date: 11/08/2021
 */

import java.io.*;
import java.util.Scanner;

public class WordCounter {
    BSTMap<String, Integer> map;
    int totalWordCount;

    //constructor that makes an empty BSTMap and sets the total word count to zero.
    public WordCounter(){
        map = new BSTMap<>(new AscendingString());
        totalWordCount = 0;
    }

    /*
    generates the word counts from a file of words.
    This method should use the BufferedReader to read
    in the file one line at a time. It should also use
    the String's split method to separate each line into
    words. Your code will be similar to the Board class
    file reader in the Sudoku project, including the
    need to use a try-catch structure to enclose your code.
     */
    public void analyze(String filename) {
        try {
            FileReader fReader = new FileReader(filename);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                // split line into words. The regular expression can be interpreted
                // as split on anything that is not (^) (a-z or A-Z or 0-9 or ').
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();
                    if(word.length() != 0){
                        if(map.get(word) == null){
                            map.put(word, 1);
                            totalWordCount++;
//                            System.out.println("Added: " + word);
                        }else{
                            map.put(word, map.get(word) + 1);
                            totalWordCount++;
//                            System.out.println(word + " " + map.get(word));
                        }
                    }
                    // Might want to check for a word of length 0 and not process it
                    // Write code to update the map
                }
                line = bReader.readLine();
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    returns the total word count (note that this is
    not the number of unique words, which would be
    the size of the BSTMap - it is the total number
    of words in the document that was originally read in).
     */
    public int getTotalWordCount(){
        return totalWordCount;
    }

    //returns the number of unique words, which should be the size of the BSTMap.
    public int getUniqueWordCount(){
        return map.size();
    }

    //returns the frequency value associated with this word.
    public int getCount( String word ){
        return map.get(word);
    }

    /*
    returns the value associated with this
    word divided by the total word count.
    Use a cast to ensure this is a floating point calculation.
     */
    public double getFrequency( String word ){
        if(map.get(word) == null){
            return 0;
        }
        return (double)map.get(word) /  totalWordCount;
    }

    //creates file of the contents of the map
    public void writeWordCountFile( String filename ) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        FileWriter entry = new FileWriter(file);
        entry.write("Total Word Count: " + this.getTotalWordCount() + "\n");
        for(KeyValuePair current: map.entrySet()){
            entry.write(current.toString());
            entry.write("\n");
        }
        entry.close();
    }

    //reads the contents of a word count file and reconstructs the fields of the WordCount object
    public void readWordCountFile( String filename ) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bReader = new BufferedReader(fileReader);
        String line = bReader.readLine();
        while(line != null){
            String[] keysAndValues = line.split(" ");
            for(String current : keysAndValues){
                System.out.println(current);
            }
            if(keysAndValues.length <= 2){
                map.put(keysAndValues[0], Integer.valueOf(keysAndValues[1]));
                totalWordCount += Integer.valueOf(keysAndValues[1]);
            }
            line = bReader.readLine();
        }
        fileReader.close();
        bReader.close();
    }
//
//    public String toString(){
//        String result = "";
//        for()
//    }

    //testing class and determining how long it takes to analyze each file
    public static void main(String[] args) throws IOException {
        WordCounter test = new WordCounter();
        test.analyze("/Users/junnanshimizu/IdeaProjects/WordTrends/src/counttest");
        test.writeWordCountFile("test.txt");
        for(KeyValuePair current : test.map.entrySet()){
            System.out.println(current.toString());
        }

//        Scanner kboard = new Scanner(System.in);
//        System.out.println("File Absolute Path: ");
//        String file = kboard.nextLine();
//        System.out.println("Time 1: " + System.currentTimeMillis());
//        test.analyze(file);
//        System.out.println("Time 2: " + System.currentTimeMillis());
//        System.out.println("Total Word Count: " + test.getTotalWordCount());
//        System.out.println("Unique Word Count: " + test.getUniqueWordCount());
//        System.out.println(test.map.toString(test.map.getRoot()));
//        test.writeWordCountFile("test.txt");
//        System.out.println("Unique Words: " + test.getUniqueWordCount());
//        kboard.close();
    }
}
