/*Pat Eizenga
 * 9/23/2024
 * Purpose: Practicing with Linked List Arrays for processing large data files.
 */

/**LinkedList class to be called from main in WorldTemps.java
 */
public class LinkedList {
	private Reading head = null;
	private Reading tail = null;

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
			if (current.getAvgTemp() != -99.0)
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

}
