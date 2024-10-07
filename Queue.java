/**Pat Eizenga
 * 10/2/2024
 * Purpose: Unclear. Difference between Queue and LinkedList
 */

/**Queue class
 */
public class Queue {
	private Client head = null;
	private Client tail = null;
	
	public void add(Client element)
	{
		if (head == null)
		{
			head = element;
			tail = element;
			System.out.println("New head\n");
		}
		else 
		{
			tail.setNext(element);
			tail = tail.getNext();
			//System.out.println("Moving tail");
		}
	}
	
	/**
	 * 
	 * @return c
	 */
	public Client serve()
	{
		Client current = head;
		head = head.getNext();
		
		return current;
	}
	
	/**Checks for more Clients.
	 * 
	 * @return head
	 */
	public Client peek()
	{
		return head;
	}

}
