/**
 * File: WordFrequency.java
 * Author: Junnan Shimizu
 * Date: 11/08/2021
 */

public class KeyValuePair<K, V>{
    K key;
    V value;

    //the constructor initializing the key and value fields.
    public KeyValuePair(K k, V v){
        this.key = k;
        this.value = v;
    }

    //returns the key.
    public K getKey(){
        return key;
    }

    //returns the value.
    public V getValue(){
        return value;
    }

    //sets the value.
    public void setValue( V v ){
        this.value = v;
    }

    //returns a String containing both the key and value.
    public String toString(){
        String result = getKey() + " " + getValue();
        return result;
    }

    public static void main(String[] args){
        KeyValuePair<String, Integer> test = new KeyValuePair<>("a", 10);
        System.out.println(test.toString());
        test.setValue(12);
        System.out.println(test.toString());
    }
}
