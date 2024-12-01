/**Pat Eizenga
 * 11/4/2024
 * Purpose: To practice the different forms of Sorts, starting with BubbleSort.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;



/**
 * 
 */
public class Sort {

	public static long comparisons = 0;
	public static long swaps = 0;

	/**Reads a large amount of data from a provided file and sorts them by different algorithms.
	 * 
	 * @param args NOT USED
	 */
	public static void main(String[] args) {

		// bubble sort = pairs of adjacent elements are compared, and the elements
		//		            swapped if they are not in order.

		//				 Quadratic time O(n^2) ???
		//				 small data set = okay-ish
		//				 large data set = BAD (22 minutes?)

		//legacy code from mini array for testing bubble sort
		//int array[] =  {9, 1, 8, 2, 7, 3, 6, 4, 5};
		int[] number = new int[1000000];
		//provided data file with known number of values
		String inFile = "sortme1000000.txt";

		importFile(number, inFile);

		for (int count = 10; count <= 1000000; count *= 10)
		{
			//runQuickSort(number, count);
			//insertionSort(number, count);
			//selectionSort(number, count);
			//bubbleSort(number, count);
			mergeSort(number, count);
		}

		//Legacy code from the mini array
		//for(int i : number) {
		//System.out.print(i);
		//}
	}

	public static void bubbleSort(int array[], int count) {

		//Declare variables for data collection
		comparisons = 0;
		swaps = 0;

		Instant start = Instant.now();
		for(int i = 0; i < count - 1; i++)
		{
			for(int j = 0; j < count - i - 1; j++) 
			{
				comparisons++;
				if(array[j] > array[j+1]) 
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					swaps++;
				} //end if
			} //end inner j-loop
			//To verify functioning of program
			if (i % (count/100.0) == 0)
			{
				System.out.println("Sorting..." + (i*100/count) + "%");
			}
		} //end outer i-loop
		Instant finish = Instant.now();
		long elapsed = (long)Duration.between(start, finish).toMillis();
		System.out.println("Bubble Sort");
		System.out.println("Sorted: " + count +
				" Comparisions: " + comparisons +
				" Swaps: " + swaps + " Time Elapsed: " + elapsed + "ms");
	} // end sort method

	private static void selectionSort(int array[], int count)
	{
		//Start timer for tracking data
		Instant start = Instant.now();
		comparisons = 0;
		swaps = 0;

		for(int i = 0; i < count - 1; i++)
		{
			if (i % (count/100.0) == 0)
			{
				System.out.println("Sorting..." + (i*100/count) + "%");
			}
			int min = i;
			for(int j = i + 1; j < count; j++)
			{
				comparisons++;
				if(array[min] > array[j])
					min = j;
			}

			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;
			swaps++;
		}//end for
		Instant finish = Instant.now();
		long elapsed = (long)Duration.between(start, finish).toMillis();
		System.out.println("Selection Sort");
		System.out.println("Sorted: " + count +
				" Comparisions: " + comparisons +
				" Swaps: " + swaps + " Time Elapsed: " + elapsed + "ms");
	}

	private static void insertionSort(int[] array, int count) 
	{
		//Start timer
		Instant start = Instant.now();
		//Declaring data collection
		comparisons = 0;
		swaps = 0;
		for (int i = 1; i < count; i++) 
		{
			//Declares a temporary value for comparison
			int temp = array[i];
			int j = i - 1;
			//Compares elements to the left
			while (j >= 0 && array[j] > temp)
			{
				comparisons++;
				array[j + 1] = array[j];
				j--;
			}
			
			//Shifts elements to the right
			array[j + 1] = temp;
			swaps++;
			//Progress Checker Code
			if (i % (count/100.0) == 0)
			{
				System.out.println("Sorting..." + (i*100/count) + "%");
			}
		}
		Instant finish = Instant.now();
		//Stops timer
		long elapsed = (long)Duration.between(start, finish).toMillis();
		System.out.println("Insertion Sort");
		System.out.println("Sorted: " + count +
				" Comparisions: " + comparisons +
				" Swaps: " + swaps + " Time Elapsed: " + elapsed + "ms");
	}

	private static void importFile(int[] number, String inFile) {
		try 
		{
			FileReader fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);

			String line = null;
			int count = 0;

			while ((line = br.readLine()) != null)
			{
				number[count++] = Integer.parseInt(line);
			}
			br.close();
		}
		catch (IOException e)
		{
			System.out.println("File error: " + inFile);
		}
	}

	//QUICK SORT//
	//wrapper for quickSort
	public static void runQuickSort(int[] number, int count) {
		swaps = 0;
		comparisons = 0;

		Instant start = Instant.now();

		quickSort(number, 0, count - 1);

		Instant finish = Instant.now();
		long elapsed = (long) Duration.between(start, finish).toMillis();
		System.out.println("Sorted " + count + "\tComparisons: " + comparisons + 
				"\tSwaps: " + swaps + "\tMilliseconds: " + elapsed);

	}//ends method without testing

		//check if the really fast speed is accurate
			//if (count < 100) {
				//for(int i = 0; i <= count; i++) {
					//System.out.println(number[i]);
				//}//ends for loop
			//}//ends if statement
	//}//ends method - runQuickSort

	//Recursively uses more memory space than insertion, bubble, and selection sort
	private static void quickSort(int[] array, int start, int end) {
		if(end <= start) return; //base case
		int pivot = partition(array, start, end);
		//pivot - 1 becomes the right endpoint of the left partition
		quickSort(array, start, pivot - 1);
		//pivot + 1 becomes the left endpoint of the right partition
		quickSort(array, pivot + 1, end);		
	}//ends method - quickSort


	private static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int i = start - 1;
		
		//
		for(int j = start; j <= end; j++) {
			comparisons++;
			if(array[j] < pivot) {
				i++;
				//Basic variable swap
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				swaps++;
			}//ends if statement
		}//ends for loop

		i++;
		
		//variable swap again
		int temp = array[i];
		array[i] = array[end];
		array[end] = temp;
		swaps++;

		return i;
		//i is the "pivot"
	}//ends method - partition



	//MERGE SORT//
	//Similar to the Quick Sort it divides and conquers in parts
	private static void mergeSort(int[] array, int count) {
		int[] choppedArray = new int[count];
		for(int i = 0; i < count; i++) {
			choppedArray[i] = array[i];
		}//ends for loop
		swaps = 0;
		comparisons = 0;

		Instant start = Instant.now();

		mergeSort(choppedArray);

		Instant finish = Instant.now();
		long elapsed = (long) Duration.between(start, finish).toNanos();

		System.out.println("Sorted " + count + "\tComparisons: "
		+ comparisons + "\tSwaps: " + swaps + "\tMilliseconds: " + elapsed);

	}//ends method - mergeSort


	private static void mergeSort(int[] array) {
		int length = array.length;
		if (length <= 1) return; //base case needed when doing recursion

		int middle = length / 2;
		//Splits into two new subarrays by locating the middle
		int[] leftArray = new int[middle];
		int[] rightArray = new int[length - middle];

		int i = 0; //left array
		int j = 0; //right array
		
		//you don't have to declare an "i" variable for this loop again
		for(; i < length; i++) {
			if(i < middle) {
				leftArray[i] = array[i];
			} else {
				rightArray[j] = array[i];
				j++;
			}//ends if/else statement
		}//ends for loop

		//Recursively sorts left and right arrays and then merges the two
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, array);

	}//ends method - mergeSort


	private static void merge(int[] leftArray, int[] rightArray, int[] array) {
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0; //indices for each array

		//check the conditions for merging
		while(l < leftSize && r < rightSize) {
			comparisons++;
			//As long as there are still elements in each array it keeps adding
				//to original array
			if(leftArray[l] < rightArray[r]) {
				array[i] = leftArray[l];
				i++;
				l++;
			} else {
				array[i] = rightArray[r];
				swaps++;
				i++;
				r++;
			}//ends if/else statement
		}//ends while loop

		while(l < leftSize) {
			array[i] = leftArray[l];
			i++;
			l++;
		}//ends while loop (checking if l is less than leftSize)

		while(r < rightSize) {
			array[i] = rightArray[r];
			i++;
			r++;
		}//ends while loop (checking if r is less than rightSize)
	}//ends method - merge


}//ends class - Sort

