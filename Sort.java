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

		//previously used mini array for testing bubble sort
		//int array[] =  {9, 1, 8, 2, 7, 3, 6, 4, 5};
		int[] number = new int[1000000];
		//provided data file with known number of values
		String inFile = "sortme1000000.txt";

		importFile(number, inFile);

		for (int count = 10; count <= 1000000; count *= 10)
		{
			insertionSort(number, count);
			//selectionSort(number, count);
			//bubbleSort(number, count);
		}

		//Legacy code from the mini array
		//for(int i : number) {
		//System.out.print(i);
		//}
	}

	public static void bubbleSort(int array[], int count) {

		long comparisons = 0;
		long swaps = 0;

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
		Instant start = Instant.now();
		long comparisons = 0;
		long swaps = 0;

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
		long comparisons = 0;
		long swaps = 0;
		for (int i = 1; i < count; i++) 
		{
			int temp = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > temp)
			{
				comparisons++;
				array[j + 1] = array[j];
				j--;
			}
			array[j +1] = temp;
			swaps++;
		}
		Instant finish = Instant.now();
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
}
