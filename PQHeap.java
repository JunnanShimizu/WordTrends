import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PQHeap<T>{
    private Object[] heap;
    private int size;
    private Comparator<T> comp;

    // a constructor that initializes the empty heap, sets the size to zero, and stores the comparator.
    public PQHeap(Comparator comp){
        this.size = 0;
        this.comp = comp;
        this.heap = new Object[5];
    }

    public int parent(int position){
        return (position - 1) / 2;
    }

    public int leftChild(int position){
        return (position * 2) + 1;
    }

    public int rightChild(int position){
        return (position * 2) + 2;
    }

    private boolean hasParent(int index){
        return parent(index) >= 0;
    }

    private boolean hasLeftChild(int index){
        return leftChild(index) < size;
    }

    private boolean hasRightChild(int index){
        return rightChild(index) < size;
    }

    public Object peak(){
        if(size == 0){
            return null;
        }else{
            return heap[0];
        }
    }

    // removes and returns the highest priority element from the heap.
    // Be sure to use the Comparator to reshape/reheap the heap.
    // You may want to add private methods to handle the reheap process.
    public T remove(){
        if(size == 0){
            return null;
        }

        T temp = (T) heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifyDown();
        return temp;
    }

    // returns the number of elements in the heap.
    public int size() {
        return this.size;
    }

    // adds the value to the heap and increments the size.
    // Be sure to use the Comparator to reshape/reheap the heap.
    // You may want to add private methods to handle the reheap process.
    public void add(T obj) {
        testSize();
        heap[size] = obj;
        size++;
        heapifyUp();
    }

    //fixes any values that conflict with the definition of a heap
    public void heapifyUp(){
        int index = size - 1;
        while(hasParent(index) && comp.compare((T) heap[parent(index)], (T) heap[index]) < 0){
            swap(parent(index), index);
            index = parent(index);
        }
    }

    //fixes any values that conflict with the definition of a heap
    public void heapifyDown(){
        int index = 0;
        while(hasLeftChild(index)){
            int smallerChildIndex = leftChild(index);
            if(hasRightChild(index) && comp.compare((T) heap[leftChild(index)], (T) heap[rightChild(index)]) < 0){
                smallerChildIndex = rightChild(index);
            }

            if(comp.compare((T) heap[smallerChildIndex], (T) heap[index]) < 0){
                break;
            }else{
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    //swaps two values
    public void swap(int indexOne, int indexTwo){
        Object temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    //doubles the size of the heap if it is full
    public void testSize(){
        if(size == heap.length){
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    //toString method that prints the values of the heap
    public String toString(){
        String result = "";
        for(int i = 0; i < size; i++){
            result += this.heap[i].toString() + " ";
        }
        return result;
    }

    public static void main(String[] args){
        PQHeap<KeyValuePair<String,Integer>> test = new PQHeap<>(new AscendingKeyValuePair());
        test.add(new KeyValuePair<>("A", 1));
        test.add(new KeyValuePair<>("B", 2));
        test.add(new KeyValuePair<>("C", 3));
        test.add(new KeyValuePair<>("E", 5));
        test.add(new KeyValuePair<>("F", 6));
        test.add(new KeyValuePair<>("D", 4));

        test.remove();
        System.out.println(test.toString());
        test.remove();
        System.out.println(test.toString());
        test.remove();
        System.out.println(test.toString());
    }
}
