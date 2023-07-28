import java.util.ArrayList;
import java.util.Scanner;

public class WordTrendsFinder {
    public static void main(String[] args){
        WordCounter test = new WordCounter();
        ArrayList<String> files = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        Scanner kboard = new Scanner(System.in);

        System.out.println("Which files would you like to compare? Type 'done' when done:");
        for(int i = 1; i < 100; i++){
            String current = kboard.nextLine();
            if(current.equals("done")){
                break;
            }
            files.add(current);
        }

        System.out.println("Which words would you like to compare? Type 'done' when done:");
        for(int i = 1; i < 100; i++){
            String current2 = kboard.nextLine();
            if(current2.equals("done")){
                break;
            }
            words.add(current2);
        }

        for(String file : files){
            test.analyze(file);
            System.out.println(file + ":");
            for(String word : words){
                System.out.println(word + ", Frequency: " + test.getFrequency(word));
            }
            System.out.println();
        }
    }
}
