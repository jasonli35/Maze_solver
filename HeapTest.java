/*
 * @author JasonLi
 * Email: Jal042@ucsd.edu
 * This file test the heap class
 */
import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;

public class HeapTest {
	
	@Test
	public void testToArray() {
		String[] stringArray = {"Eric", "Nandini", "Rebecca", "Greg", "Juan"};
		int[] weightsArray = {0,1,2,3,4};
		Heap<Integer, String> heap = new Heap<Integer, String>(new minComparetor());
		for(int i = 0; i < 5; i++) {
			heap.add(weightsArray[i], stringArray[i]);
		}
		assertEquals(5, heap.toArray().size());
		
		String actual = heap.toString();
		//System.out.print(actual);
		List<Entry<Integer, String>> actualArray = heap.toArray();
		for(int i = 0; i < 5; i++) {
			assertNotEquals(-1, actual.indexOf(stringArray[i]));
			assertEquals(true, actualArray.get(i).value.equals(stringArray[i]));
		}
	}
	@Test
	public void testHasValidStrucutre() {
		String[] stringArray = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] weightsArray = {20, 10, 30, 5, 1, 22};
		Heap<Integer, String> heap = new Heap<Integer, String>(new minComparetor());
		for(int i = 0; i < 6; i++) {
			heap.add(weightsArray[i], stringArray[i]);
		}
		assertEquals(6, heap.toArray().size());
		
		String actual = heap.toString();
		System.out.println(actual);
		String expect = "Greg Paul Rebecca Garo Eric Nandini";
		List<Entry<Integer, String>> actualArray = heap.toArray();
		assertEquals("Greg", actualArray.get(0).value);
		assertEquals("Paul", actualArray.get(1).value);
		assertEquals("Rebecca", actualArray.get(2).value);
		assertEquals("Garo", actualArray.get(3).value);
		assertEquals("Eric", actualArray.get(4).value);
		assertEquals("Nandini", actualArray.get(5).value);
		
	}
	@Test
	public void testHeapFunctionality_peek() {
		String[] stringArray = {"Eric", "Nandini", "Rebecca", "Greg", "Juan"};
		int[] weightsArray = {0,1,2,3,4};
		Heap<Integer, String> heap = new Heap<Integer, String>(new minComparetor());
		for(int i = 0; i < 5; i++) {
			heap.add(weightsArray[i], stringArray[i]);
		}
		assertEquals(true, heap.peek().value.equals(stringArray[0]));
		assertEquals(true, heap.peek().key.equals(0));
	
	}
	@Test
	public void testHeapFunctionality_removeMin() {
		String[] stringArray = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] weightsArray = {20, 10, 30, 5, 1, 22};
		Heap<Integer, String> heap = new Heap<Integer, String>(new minComparetor());
		for(int i = 0; i < 6; i++) {
			heap.add(weightsArray[i], stringArray[i]);
		}
		System.out.println(heap.toString());
		assertEquals("Greg", heap.poll().value);
		heap.poll();
		assertEquals("Eric", heap.poll().value);
		assertEquals("Garo", heap.poll().value);
		assertEquals("Rebecca", heap.poll().value);
		assertEquals("Nandini", heap.poll().value);
		
	}
	@Test
	public void testHeapFunctionality_removeMax() {
		String[] stringArray = {"Garo", "Eric", "Nandini", "Paul", "Greg", "Rebecca"};
		int[] weightsArray = {20, 10, 30, 5, 1, 22};
		Heap<Integer, String> heap = new Heap<Integer, String>(Collections.reverseOrder(new minComparetor()));
		for(int i = 0; i < 6; i++) {
			heap.add(weightsArray[i], stringArray[i]);
		}
		System.out.println(heap.toString());
		assertEquals("Nandini", heap.poll().value);
		assertEquals("Rebecca", heap.poll().value);
		assertEquals("Garo", heap.poll().value);
		assertEquals("Eric", heap.poll().value);
		assertEquals("Paul", heap.poll().value);
		assertEquals("Greg", heap.poll().value);
		
	}

}

class minComparetor implements Comparator<Integer>{
	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1 < o2) {
			return 1;
		}
		else if(o1 > o2) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
