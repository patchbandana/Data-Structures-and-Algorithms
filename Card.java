/**Pat Eizenga
 * 10/16/2024
 * Purpose: To encapsulate the Card object for playing War
 */

/**Card class. Must contain 13 numerical values in 4 suits.
 */
public class Card {
	private String number = "";
	private char suit = ' ';
	private int value = 0;
	private Card next;


	/**Card constructor with all parameters. Reusable for other games.
	 * @param number
	 * @param suit
	 * @param value
	 */
	public Card(String number, char suit, int value) {
		setNumber(number);
		setSuit(suit);
		setValue(value);
	}

	/**Accesses the number of the card
	 * @return the number of the card
	 */
	public String getNumber() {
		return number;
	}

	/**Mutates the number of the card
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**Returns the Suit of the card
	 * @return the suit of the card
	 */
	public char getSuit() {
		return suit;
	}

	/**Changes the suit of the card
	 * @param suit the suit to set
	 */
	public void setSuit(char suit) {
		this.suit = suit;
	}

	/**Get the value of the card
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**Set the value of the card
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
