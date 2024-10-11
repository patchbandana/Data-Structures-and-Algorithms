/**Pat Eizenga
 * 10/2/2024
 * Purpose: To move through the csv file, reading and operating on the data within
 */

/** LinkedList class will move through the Client elements.
 */
public class LinkedList {
	private Client head = null;
	private Client tail = null;

	public void add(Client element)
	{
		if (head == null)
		{
			head = element;
			tail = element;
			//System.out.println("New head");
		}
		else 
		{
			tail.setNext(element);
			tail = tail.getNext();
			//System.out.println("Moving tail");
		}
	}

	public Client find(long account)
	{
		Client current = head;
		while ((current != null) && (current.getAccountNumber() != account))
		{
			current = current.getNext();
		}
		return current;
	}

	public void print()
	{
		Client current = head;
		while (current.getNext() != null)
		{
			System.out.println(current.getFirstName() + " " + current.getLastName());
			current = current.getNext();
		}
	}

	public Client getFirst() {
		return head;
	}

	public void setFirst(Client next) {
		head = next;
	}
}
