/* Pat Eizenga
 * 2024-09-04
 * Description: Standard Array with minmax and printout functions.
 */
import java.util.Scanner;

public class Array1 {

	public static void main(String[] args) {
		int maxNums = 20;
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

		int max = findMax(nums, myMax);
		int min = findMin(nums, myMax);
		printout(nums, myMax, min, max);

		s.close();
	}

	public static int findMax(int[] numbers, int myMax)
	{
		int max = numbers[0];

		for (int x = 1; x < myMax; x++)
		{
			if (numbers[x] > max)
			{
				max = numbers[x];
			}
		}

		return max;
	}

	public static int findMin(int[] numbers, int myMax)
	{
		int min = numbers[0];

		for (int x = 1; x < myMax; x++)
		{
			if (numbers[x] < min)
			{
				min = numbers[x];
			}
		}

		return min;
	}

	public static void printout(int[] numbers, int myMax, int min, int max)
	{
		System.out.println("*********************************************");

		System.out.println("List of numbers: ");
		for (int current = 0; current < myMax; current++)
		{
			if (current <= (myMax - 2))
				System.out.print(numbers[current] + ",");
			else
				System.out.print(numbers[current]);
		}

		System.out.println("\nMinimum: " + min);
		System.out.println("Maximum: " + max);

		System.out.println("*********************************************"); 
	}
}