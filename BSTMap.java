/*
***Junnan Shimizu***

Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
    // fields here
    private TNode root;
    private int size;
    Comparator<K> comp;

    // constructor: takes in a Comparator object
    public BSTMap(Comparator<K> comp ) {
        // initialize fields here
        this.root = null;
        this.size = 0;
        this.comp = comp;
    }

    // adds or updates a key-value pair
    // If there is already a pair with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    // returns the old value or null if no old value existed
    public V put( K key, V value ) {
        //check for and handle the special case
        // call the root node's put method
        if(root == null) {
            root = new TNode(key, value);
            size = 1;
        }else{
            root.put(key,value,comp);
        }

        // stub code
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if(root.get(key,comp) != null){
            return true;
        }else{
            return false;
        }
    }

    // gets the value at the specified key or null
    public V get( K key ) {
        // check for and handle the special case
        // call the root node's get method
        if(root == null || root.get(key,comp) == null){
            return null;
        }else{
            return root.get(key,comp);
        }
    }

    @Override
    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet() {
        return this.keySetRecursive(this.root);
    }
    ArrayList<K> keys = new ArrayList<>();
    public ArrayList<K> keySetRecursive(TNode root){
        if(root != null){
            keys.add((K)root.valuePair.getKey());
            keySetRecursive(root.left);
            keySetRecursive(root.right);
        }
        return keys;
    }

    @Override
    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values() {
        return this.valuesRecursive(this.root);
    }
    ArrayList<V> values = new ArrayList<>();
    public ArrayList<V> valuesRecursive(TNode root){
        if(root != null){
            values.add((V)root.valuePair.getValue());
            valuesRecursive(root.left);
            valuesRecursive(root.right);
        }
        return values;
    }

    @Override
    // return an ArrayList of pairs.
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        return this.entrySetRecursive(this.root);
    }
    ArrayList<KeyValuePair<K, V>> pairs = new ArrayList<>();
    public ArrayList<KeyValuePair<K,V>> entrySetRecursive(TNode root){
        if(root != null){
            pairs.add(root.valuePair);
            entrySetRecursive(root.left);
            entrySetRecursive(root.right);
        }
        return pairs;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    public TNode getRoot(){
        return this.root;
    }

    // Write stubs (functions with no code) for the remaining
    // functions in the MapSet interface


    // entrySet notes: For the sake of the word-counting project, the
    // pairs should be added to the list by a pre-order traversal.

    public String toString(TNode root){
        String result = "";
        if(root == null){
            return "";
        }

        result += root.toString();
        result += toString(root.left);
        result += toString(root.right);
        return result;
    }

    // You can make this a nested class
    private class TNode {
        // need fields for the left and right children
        // need a KeyValuePair to hold the data at this node
        TNode left, right;
        KeyValuePair valuePair;

        // constructor, given a key and a value
        public TNode( K k, V v ) {
            // initialize all of the TNode fields
            left = null;
            right = null;
            valuePair = new KeyValuePair(k,v);
        }

        // Takes in a key, a value, and a comparator and inserts
        // the TNode in the subtree rooted at this node

        // Returns the value associated with the key in the subtree
        // rooted at this node or null if the key does not already exist
        public V put( K key, V value, Comparator<K> comp ) {
            // implement the binary search tree put
            // insert only if the key doesn't already exist
            if (key == null){
                return null;
            }

            if(comp.compare((K)(valuePair.getKey()), key) == 0){
                V old = this.get(key,comp);
                this.valuePair = new KeyValuePair(key, value);
//                System.out.println("Test: " + value);
                return old;
            }else if (comp.compare((K)this.valuePair.getKey(), key) < 0){
                if(this.left == null){
                    this.left = new TNode(key, value);
                    size++;
                }else{
                    this.left.valuePair.setValue(this.left.put(key,value,comp));
                }
            }else{
                if(this.right == null){
                    this.right = new TNode(key,value);
                    size++;
                }else{
                    this.right.valuePair.setValue(this.right.put(key,value,comp));
                }
            }
            return (V)this.valuePair.getValue();
        }

        // Takes in a key and a comparator
        // Returns the value associated with the key or null
        public V get( K key, Comparator<K> comp ) {
            if(key == null){
                return null;
            }

            if(comp.compare((K)this.valuePair.getKey(), key) == 0){
                return (V)this.valuePair.getValue();
            }

            if(comp.compare((K)valuePair.getKey(), key) < 0){
                if(this.left != null){
                    return this.left.get(key,comp);
                }else{
                    return null;
                }
            }else{
                if(this.right != null){
                    return this.right.get(key,comp);
                }else{
                    return null;
                }
            }
        }

        // Any additional methods you want to add below, for
        // example, for building ArrayLists

        public String toString(){
            String result = "";
            result += this.valuePair.key + ", " + this.valuePair.value + "\n";
            return result;
        }

    }// end of TNode class

    // test function
    public static void main( String[] argv ) {
            // create a BSTMap
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );
//        bst.put( "twenty", 20 );
//        bst.put("thirty", 30);
//        bst.put( "ten", 10 );
//        bst.put( "eleven", 11 );
//        bst.put( "five", 5 );
//        bst.put( "six", 4 );
//        bst.put("six", 6);
//        bst.put("twenty", 21);

//        System.out.println( bst.get( "ten" ) );
//        System.out.println( bst.get( "six" ) );
//        System.out.println( bst.get( "twenty" ) );
//        System.out.println( bst.get( "five" ) );
//        System.out.println( bst.get( "ten" ) );

//        System.out.println(bst.toString(bst.root));

        for(KeyValuePair current : bst.entrySet()){
            System.out.println(current.key + ", " + current.value);
        }
        // put more test code here

        for(String current : bst.keySet()){
            System.out.println(current);
        }

        for(int current : bst.values()){
            System.out.println(current);
        }
    }
}