/* 
 * This file is how you might test out your code.  Don't submit this, and don't 
 * have a main method in SortTools.java.
 */

package assignment1;

import java.util.Arrays;

public class Main {
	public static void main(String [] args) {
		int [] test = new int[] {1,2,4,4,5,6,7};
		System.out.println(Arrays.toString(SortTools.insertGeneral(test, 4, 1)));
		
		int [] unsorted = new int[] {1,5,11,63235,21,215,2,1};
		int [] sorted = SortTools.insertSort(unsorted, unsorted.length);
		System.out.println(Arrays.toString(sorted));

//		int[] unsorted = new int[] {5, 17, 2, 77, 25, 1, 1};
//		int [] sorted = SortTools.insertSort(unsorted, 7);
//		System.out.println(Arrays.toString(sorted));
	}
}
