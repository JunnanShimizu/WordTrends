import java.util.Comparator;

public class AscendingKeyValuePair implements Comparator<KeyValuePair<String,Integer>> {
    public int compare(KeyValuePair<String,Integer> one , KeyValuePair<String, Integer> two){
//        if(one == null){
//            return -1;
//        }
//
//        if(two == null){
//            return 1;
//        }

        int difference = one.getValue() - two.getValue();

        if(difference == 0){
            return 0;
        }

        if(difference > 0){
            return 1;
        }else{
            return -1;
        }
    }
}
