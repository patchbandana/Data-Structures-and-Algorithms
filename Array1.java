/* Pat Eizenga
 * 2024-09-04
 * Description: Standard Array with minmax and printout functions.
 */
import java.util.Scanner;

public class Array1 {
	
	public static int maxNums = 200;

	public static void main(String[] args) {
		int myMax = 0;
		int[] nums = new int[maxNums];
		//String[] students = new String[maxNums];
		Scanner s = new Scanner(System.in);

		boolean stillGoing = true;

		System.out.println("Enter up to " + maxNums + " integers \'-1\' to end\n.");

		while ((myMax < maxNums) && (stillGoing == true))
		{
			System.out.print("Enter integer #" + (myMax + 1) + ": ");

			int temp = s.nextInt();
			if (temp >= 0)
			{
				nums[myMax] = temp;
				myMax++; 
			}
			else {
				stillGoing = false;
			}
		}
		
		
		add(nums, myMax, 255);
		printout(nums, myMax);
		
		int max = findMax(nums, myMax);
		System.out.printf("\nMax: %d\n", nums[max]);
		myMax = delete(nums, myMax, findMax(nums, myMax));
		
		int min = findMin(nums, myMax);
		System.out.printf("Min: %d\n", nums[min]);
		myMax = delete(nums, myMax, findMin(nums, myMax));

		s.close();
	}

	public static int add(int[] numbers, int myMax, int newNum)
	{
		if (myMax <= maxNums)
		{
			numbers[myMax] = newNum;
			myMax++;
			return myMax;
		}
		else
		{
			System.out.println("Did not add to array. Array at maximum length.");
			return -1;
		}
	}
	
	public static int delete(int[] numbers, int myMax, int idx)
	{
		numbers[idx] = numbers[myMax - 1];
		myMax--;
		return myMax;
	}

	public static int findMax(int[] numbers, int myMax)
	{
		int max = 0;

		for (int x = 1; x < myMax; x++)
		{
			if (numbers[x] > numbers[max])
			{
				max = x;
			}
		}

		return max;
	}

	public static int findMin(int[] numbers, int myMax)
	{
		int min = 0;

		for (int x = 1; x < myMax; x++)
		{
			if (numbers[x] < numbers[min])
			{
				min = x;
			}
		}

		return min;
	}

	public static void printout(int[] numbers, int myMax)
	{
		System.out.println("*********************************************");

		System.out.println("List of numbers: ");
		for (int current = 0; current < myMax; current++)
		{
			if (current < (myMax - 1))
				System.out.print(numbers[current] + ",");
			else
				System.out.print(numbers[current]);
		}

		System.out.println("\n*********************************************"); 
	}
}