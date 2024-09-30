/*Pat Eizenga
 * 9/23/2024
 * Purpose: Practicing with Linked List Arrays for processing large data files.
 */

/**LinkedList class to be called from main in WorldTemps.java
 */
public class LinkedList {
	private Reading head = null;
	private Reading tail = null;
	public static int maxYears = 30;

	public void add(Reading element)
	{
		if (head == null)
		{
			head = element;
			tail = element;
			System.out.println("New head");
		}
		else 
		{
			tail.setNext(element);
			tail = tail.getNext();
			//System.out.println("Moving tail");
		}

	}

	public void print()
	{
		Reading current = head;
		while (current.getNext() != null)
		{
			System.out.println(current.getCity() + " - " + current.getAvgTemp());
			current = current.getNext();
		}
	}

	/**
	 * Deletes a value given by the user and returns the number of values deleted
	 * @param value passed by user
	 * @return count incremented per value deleted
	 */
	public int deleteValue(double value)
	{
		int count = 0;
		Reading current = head;
		Reading previous = current;
		while (current != null)
		{
			if(current.getAvgTemp() == value)
			{
				previous.setNext(current.getNext());
				current = current.getNext();
				count++;
			}
			else 
			{
				previous = current;
				current = current.getNext();
			}
		}

		return count;
	}

	public Reading findMax()
	{
		Reading maxTemp = head;
		Reading current = head;

		while (current != null)
		{
			if (current.getAvgTemp() > maxTemp.getAvgTemp())
			{
				maxTemp = current;
			}
			current = current.getNext();
		}
		return maxTemp;
	}

	public Reading findMin()
	{
		Reading minTemp = head;
		Reading current = head;

		while (current != null)
		{
			//if (current.getAvgTemp() != -99.0)
			{
				if (current.getAvgTemp() < minTemp.getAvgTemp())
				{
					minTemp = current;
				}
			}
			current = current.getNext();
		}
		return minTemp;
	}

	public void worldAvgByYear()
	{
		Reading current = head;
		double[] totals = new double[maxYears];
		int[] counts = new int[maxYears];
		double[] avg = new double[maxYears];
		int startYear = 1995;

		while(current != null)
		{
			if (current.getAvgTemp() != -99)
			{
				int index = current.getYear() - startYear;
				totals[index] += current.getAvgTemp();
				counts[index]++;
				avg[index] = totals[index] / counts[index];
			}//end if
			current = current.getNext();
		} //end while loop

		System.out.println("World Temps:");
		System.out.println("Year - Average");
		System.out.println("**************");
		for (int x = 0; x < maxYears; x++)
		{
			System.out.println((x + startYear) + " - " + avg[x]);

		}//end for loop
	}//end worldAvgByYear

	public void usAvgByYear()
	{
		Reading current = head;
		double[] totals = new double[maxYears];
		int[] counts = new int[maxYears];
		double[] avg = new double[maxYears];
		int startYear = 1995;

		while(current != null)
		{
			if (current.getCountry().equals("US"))
			{
				if (current.getAvgTemp() != -99)
				{
					int index = current.getYear() - startYear;
					totals[index] += current.getAvgTemp();
					counts[index]++;
					avg[index] = totals[index]/counts[index];
				}//end nested if
			}//end initial if
			current = current.getNext();
		} //end while loop
		System.out.println("\nUS Temps:");
		System.out.println("Year - Average");
		System.out.println("**************");
		for (int x = 0; x < maxYears; x++)
		{
			System.out.println((x + startYear) + " - " + avg[x]);
			//System.out.println(avg[x]);

		}//end for loop
	}//end worldAvgByYear

}//end LinkedList
