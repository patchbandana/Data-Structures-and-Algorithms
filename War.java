/**Pat Eizenga
 * 10/16/2024
 * Purpose: To create the card game war as a text based program.
 */

import java.util.Random;
/**The card game of War
 */
public class War {

	/**The main program for initiating War.
	 * @param args NOT USED
	 */
	public static void main(String[] args) {
		int numberOfPlayers = 2;
		int numberOfCards = 52;

		//Declare a new Deck of Cards of standard size
		Card[] deck = new Card[numberOfCards];
		//Makes a deck of cards
		deck = makeDeck(numberOfCards);
		//Shuffles cards in Deck
		shuffle(deck);
		shuffle(deck);
		shuffle(deck);
		shuffle(deck);
		
		//Player 1 and 2 Stacks
		Stack p1stack = new Stack();
		Stack p2stack = new Stack();
		
		//Winning condition stacks
		Stack w1stack = new Stack();
		Stack w2stack = new Stack();
		
		/*Deals out the entire deck to both players equally, one player gets odds
			and the other even numbered cards*/
		deal(deck, p1stack, p2stack);
	}

	private static Card[] makeDeck(int numberOfCards) {
		//Creates an array of Card objects called deck
		Card[] deck = new Card[numberOfCards];

		int cardIndex = 0;
		//Checks for which suit the card is
		for (int suit = 0; suit <= 3; suit++)
		{
				String number = "";
				char symbol = switch (suit) 
						{
						case 0 -> '\u2660';
						case 1 -> '\u2666';
						case 2 -> '\u2663';
						case 3 -> '\u2764';
						default -> ' ';
						};

						for(int val = 2; val <= 14; val++)
						{
							number = switch (val) {
							case 11 -> "J";
							case 12 -> "K";
							case 13 -> "Q";
							case 14 -> "A";
							default -> "" + val;
							};
							Card card = new Card(number, symbol, val);
							deck[cardIndex] = card;
							cardIndex++;
						}
		}
		return deck;
	}
	public static void shuffle(Card[] deck) {
		Random random = new Random();

		for (int i = 0; i < deck.length; i++) {
			int randomIndex = random.nextInt(deck.length);
			Card temp = deck[randomIndex];
			deck[randomIndex] = deck[i];
			deck[i] = temp;
		}
	}

	public static void deal(Card[] deck, Stack p1stack, Stack p2stack) {
		for (var i = 0; i < deck.length; i += 2) {
			Card c1 = deck[i];
			Card c2 = deck[i + 1];

			p1stack.push(c1);
			p2stack.push(c2);
		}
	}
	
	/* void checkEmpty(Stack p1hand, Stack p1win)
	 * 	if (pstack.isEmpty() && wstack.IsEmpty()) {
	 * 		print
	 * 		print
	 * 		print
	 * 		exit
	 * 	else if (pstack.isEmpty()) {
	 * 		while p1win is not empty 
	 * 			pop from the winstack
	 * 			push that card to p1hand
	 * 		end while
	 * 	if p1hand is empty & p1win is empty,
	 * 		then the other player has won
	 * 
	 * void playHands(Stack p1hand, Stack p2hand, Stack p1Win, Stack p2Win)
	 * while:
	 * 	check empty on p1 & p2 stacks
	 * 	then pop card1 and card2 from each
	 * 	get values of each & compare
	 * 		if v1 > v2
	 * 			push (card1, card 2 to p1winstack)
	 * 		else if v1 < v2
	 * 			push (card 2, card 1 to p2winstack)
	 * 		else its a tie
	 * 			tied = true
	 * 			while tied
	 * 				push cards onto center stack
	 * 				if cards are tied, continue,
	 * 					else tied = false
	 * 					push card1, card2 to the winner
	 * 					and move the center stack to the winner
	 * end while
	 * 
	 * private void moveStack(Stack winningStack, Stack getAll)
	 * 	while (!getAll.isEmpty())
	 * 		Card card = (Card getAll.pop();
	 * 		if (card == null)
	 * 			return
	 * 	winningStack.push(Card)
	 *getAll is the name for the center tie stack pot
	 */
}