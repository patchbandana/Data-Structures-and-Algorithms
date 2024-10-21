/**Pat Eizenga
 * 10/16/2024
 * Purpose: To practice the data structure, "Stack".
 */

/**The Stack class: push, pop, empty, notEmpty
 */
public class Stack {
	Card head = null;

	/**Push method. Removes the top card and assigns the next card to be the top card.
	 * 
	 * @param card passed in to be the new top of the deck.
	 */
	public void push(Card card)
	{
		card.setNext(head);
		head = card;
	}

	/**Draws the current card and assigns the next card as the top card.
	 * 
	 * @return card that was drawn
	 */
	public Card pop()
	{
		Card card = head;
		head = head.getNext();
		return card;
	}

	/**Checks to make sure the stack of cards is empty.
	 * 
	 * @return true if the top card doesn't exist
	 */
	public boolean empty() {
		boolean answer = false;
		if (head == null)
		{
			answer = true;
		}
		return answer;

	}
	/**Checks to make there exists a Stack of Cards
	 * 
	 * @return true if there is a Card in the Stack
	 */
	public boolean notEmpty() {
		boolean answer;
		if (head != null)
			answer = true;
		else
			answer = false;
		return answer;
	}
}
