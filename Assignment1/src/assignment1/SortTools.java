// SortTools.java 
/*
 * EE422C Project 1 submission by
 * Replace <...> with your actual data.
 * <Rajat Ahuja>
 * <RA29697>
 * <15455>
 * Spring 2018
 * Slip days used: 
 */
package assignment1;
public class SortTools {
	/**
	 * This method tests to see if the given array is sorted.
	 * @param x is the array
	 * @param n is the size of the input to be checked
	 * @return true if array is sorted
	 */
	public static boolean isSorted(int[] x, int n) {	//done
		if (x.length != 0 && n != 0) {	//O(1)
			for (int i = 0; i < n-1; i++) {	//O(n)
				if (x[i] > x[i + 1]) {	//O(1)
					return false;		
				}
			}
			return true;					
		}
		else {
			return false;
		}
	}

	public static int find(int[] nums, int n, int v) {	//binary search to find an element in O(n) time
         int low = 0;
         int high = n - 1;        
         while(high >= low) { 
             int middle = (low + high) / 2;
             if(nums[middle] == v) {
                 return middle; //we have found our element
             }
             if(nums[middle] < v) { //splits our array
                 low = middle + 1;
             }
             if(nums[middle] > v) { //splits our array
                 high = middle - 1;
             }
        }
        return -1;	//what we return if our element is not in the array

	}

	public static int[] insertGeneral(int[] nums, int n, int v) {
		if (find(nums, n, v) != -1) {	//check to see nums already has v 
			int [] myArray = new int[n];	
			for (int i = 0; i < n; i++) { //create a new array that is equal to nums
				myArray[i] = nums[i];
			}
			return myArray; 
		} 
		else { 
			int [] newArray = new int[n+1]; //create an n+1 size array to fit nums[i] elements and v
			for (int i = 0; i < n; i++) {
				newArray[i] = nums[i]; //copy over nums to newArray
			}
			insert(newArray, v); //calls our created insert function to put v into our new array
			return newArray; 
		}
	}

	public static int insertInPlace(int[] nums, int n, int v) {
		if (find(nums, n, v) != -1) { //check to see if nums has v
			return n;	//if it does, return n
		}
		else {
			int [] newArray = new int[n + 1]; //create a new array w/ n+1  
			for (int i = 0; i < n; i++) {
				newArray[i] = nums[i]; //copy over nums
			}
			nums = newArray; //nums = newArray as our way to increase the size of nums from n to n+1
			insert(nums, v); //insert v into our "new" nums
			return n+1; //we return n+1 for insertInPlace
		}
	}
	
	public static int[] insertSort(int[] nums, int n) {
		for (int a = 0; a < n-1; a++) { //our first run to fix nums in linear time
			if (nums[a] > nums[a+1]) {
				int temp1 = nums[a];
				nums[a] = nums[a+1];
				nums[a+1] = temp1;
			}
		}
		if (isSorted(nums, n) == true) { //if we sorted in linear time
			return nums;	 				// we are done
		}
		else {
			for (int i = 0; i < n; i++) { //else sort in O(n) time 
				for (int j = i + 1; j < n; j++) {
					int temp = 0;
					if (nums[i] > nums[j]) { //same logic as above but happens in an iterated array
						temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
					}
				}
			}
		}
		return nums;
	}
	
	public static void insert(int[] array, int v) { //a custom function to insert elements in O(n) time
		int i;
		for(i = 0; i < array.length - 1; i++) {
			if(array[i] > v)	//finding where to place v
				break;
		}
		for(int k = array.length-2; k >= i; k--) {
			array[k+1] = array[k]; 	//shifting over all the elements
		}
		array[i] = v; 	//actually placing in v
	}
}