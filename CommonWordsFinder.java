import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CommonWordsFinder {
    private PQHeap<KeyValuePair<String,Integer>> heap;

    public CommonWordsFinder(){
        heap = new PQHeap(new AscendingKeyValuePair());
    }

    //reads a file and builds either a hashmap or a bstmap
    public void readFile(String filename) throws IOException {
        WordCounter2 map = new WordCounter2("hashmap");

        map.buildMap(map.readWords(filename));

        for(KeyValuePair current : map.arrayListReturn()){
            heap.add(current);
        }
    }

    //toString method that prints the word, the count, and the frequency
    public String toString(int topBlank){
        String result = "";
        for(int i = 0; i < topBlank; i++){
            KeyValuePair<String, Integer> temp = heap.remove();
            result += temp.toString() + ", ";
            result += (double)temp.value / heap.size() + "\n";
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        CommonWordsFinder test = new CommonWordsFinder();
        String[] inputs = new String[10];
        Scanner kboard = new Scanner(System.in);
        System.out.print("Top N Words, N = ");
        inputs[0] = kboard.nextLine();
        for(int i = 1; i < 10; i++){
            System.out.println("File Absolute Path: ");
            inputs[i] = kboard.nextLine();
            test.readFile(inputs[i]);
            System.out.println(test.toString(Integer.valueOf(inputs[0])));
        }
    }
}
