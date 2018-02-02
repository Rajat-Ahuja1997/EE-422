package assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SortToolsTest {
	
	@Test(timeout = 2000) 
	public void testIsSortedNormal() {
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(true, SortTools.isSorted(x, 6));
	}
	
	@Test(timeout = 2000) 
	public void testIsSortedZeroN() {
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(false, SortTools.isSorted(x, 0));
	}
	
	@Test(timeout = 2000)
	public void testIsSortedZeroArray() {
		int[] x = new int[]{};
		assertEquals(false, SortTools.isSorted(x, 1));
	}
	
	@Test(timeout = 2000)
	public void testFindFoundFull(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(3, SortTools.find(x, 6, 1));
	}
	
	@Test(timeout = 2000)
	public void testFindTwo() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		assertEquals(1, SortTools.find(x, 9, 10));
	}
	
	@Test(timeout = 2000)
	public void testFindNothing() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		assertEquals(-1, SortTools.find(x, 9, 15));
	}
	
	@Test(timeout = 2000)
	public void testInsertGeneral() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		int[] y = new int[]{10, 10, 15, 20, 30, 40, 60, 70, 70, 80};
		assertArrayEquals(SortTools.insertGeneral(x, 9, 15), y);
	}
	
	@Test(timeout = 2000)
	public void testInsertGeneralAlreadyExists() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		int[] y = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		assertArrayEquals(SortTools.insertGeneral(x, 9, 10), y);
	}
	
	@Test(timeout = 2000)
	public void testInsertInPlace() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		assertEquals(SortTools.insertInPlace(x, 9, 15), 10);
	}
	
	@Test(timeout = 2000)
	public void testInsertInPlaceTwo() {
		int[] x = new int[]{10, 10, 20, 30, 40, 60, 70, 70, 80};
		assertEquals(SortTools.insertInPlace(x, 9, 10), 9);
	}
	
	@Test(timeout = 2000)
	public void testInsertSort() {
		int [] unsorted = new int[] {1,5,11,63235,21,215,2,1};
		int [] sorted = new int [] {1, 1, 2, 5, 11, 21, 215, 63235};
		assertArrayEquals(SortTools.insertSort(unsorted, 8), sorted);
	}
	
	
	
	
	
	
	
	
	
	
	
}