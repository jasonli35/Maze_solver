/*
 * @author JasonLi
 * Email: Jal042@ucsd.edu
 * This file implement methods in priortyQueue interface
 */
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;



public class Heap<K, V> implements PriorityQueue<K, V>{
	
	public List<Entry<K, V>> entries;
	public Comparator<K> comparator;
	
	public Heap(Comparator<K> comparator) {
		entries = new ArrayList<Entry<K, V>>();
		entries.add(null);
		this.comparator = comparator;
	}
	// This method add a new entry to the list.
	@Override
	public void add(K k, V v) {
		entries.add(new Entry(k, v));
		bubbleUp(size());
		
 	}
	/*
	 * This method remove and return the first element in the heap 
	 * and make sure it fix the heap after remove
	 * @return  the root element in the heap
	 */
	public Entry<K, V> poll(){
		swap(1, size());
		Entry<K, V> toRemove = entries.remove(size());
		bubbleDown(1);
		return toRemove;
	}
	/*
	 * @return  the first element in the heap
	 * @throws NoSuchElementException if the list is empty
	 */

    public Entry<K, V> peek(){
    	if(isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	else {
    		return entries.get(1);
    	}
    	
    }
    
     //@return   a list of all entries
     public List<Entry<K,V>> toArray(){
    	List<Entry<K,V>> temp = new ArrayList<Entry<K,V>>();
    	for(int i = 1; i <= size(); i++) {
    		temp.add(entries.get(i));
    	}
    	return temp;
  
    }
    /*
     * @return  true if the list is empty 
     * @return  false   if the list have element
     */
    public boolean isEmpty() {
    	return size() == 0;
    }
    /*
     * A recursive method that moves the entry at the specified index to a smaller index 
     * (up the tree) while maintaining the heap structure
     */
    public void bubbleUp(int index) {
    	if(index == 1) {
    		return;
    	}
    	if(existsAndGreater(index, parent(index))) {
    		swap(index, parent(index));
    		bubbleUp(parent(index));
    	}
    	 
    }
    /*
     * A recursive method that moves the entry at the specified index to a larger index (down 
     * the tree) while maintaining the heap structure. Swap with the smaller child child with 
     * higher priority. If both children are equal and swapping is needed, swap with the left child.
     */
    public void bubbleDown(int index) {
    	if(index > size()) {
    		return;
    	}
    	if(existsAndGreater(left(index), index) || existsAndGreater(right(index), index)) {
    		if(existsAndGreater(right(index), left(index))){
    			swap(right(index), index);
    			bubbleDown(right(index));
    		}
    		else {
    			swap(left(index), index);
    			bubbleDown(left(index));
    		}
    	}
    }
    /*
     * @param  index index of the current element 
     * @return  index of the parent 
     */
    public int parent(int index) {
    	return index / 2;
    }
    /*
     * @param  index index of the current element 
     * @return  index of the left children 
     */
    public int left(int index) {
    	return 2 * index;
    }
    /*
     * @param  index index of the current element 
     * @return  index of the right children 
     */
    public int right(int index) {
    	return 2 * index + 1;
    }
    
    /*
     * This method swap two element with entries
     * @param  i1   the first index try to swap
     * @param  i2  the second index to swap
     */
    public void swap(int i1, int i2) {
    	Entry<K, V> temp = entries.get(i1);
    	entries.set(i1, entries.get(i2));
    	entries.set(i2, temp);
    }
    /*
     * @return true true if both index exist the entry at index1 is have more priority than that at index2
     * @param  index1  index of the first entry
     * @param  index2  index of the second entry
     */
    public boolean existsAndGreater(int index1, int index2) {
    	if(index1 < 1 || index1 >= entries.size()) {
    		return false;
    	}
    	if(index2 < 1 || index2 >= entries.size()) {
    		return false;
    	}
    	if(comparator.compare(entries.get(index1).key, entries.get(index2).key) > 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    //@return  the number of elements in entries
    public int size() {
    	return entries.size() - 1;
    }
    
    //@return a string representation of elements in entries
    public String toString() {
    	String result = "";
    	for(int i = 1; i <= size(); i++) {
    		//result += "Key is " + entries.get(i).key + "; value is " + entries.get(i).value + '\n';
    		result += entries.get(i).value + " ";
    	}
    	return result;
    }


}

class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }
}
