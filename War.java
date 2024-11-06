/**Pat Eizenga
 * 10/16/2024
 * Purpose: To create the card game war as a text based program.
 */

import java.util.Random;
/**The card game of War
 */
public class War {

	public static String p1Name = "P1";
	public static String p2Name = "P2";
	public static int numberOfPlayers = 2;
	public static int numberOfCards = 52;
	public static int round = 0;
	public static int maxRound = 999;

	/**The main program for initiating War game, creates stacks
	 * and initiates the the deck.
	 * @param args NOT USED
	 */
	public static void main(String[] args) {
		boolean running = true;
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

		System.out.println("### P1 STACK:");
		printStack(p1stack);
		System.out.println("### P2 STACK:");
		printStack(p2stack);

		while (running && round < maxRound) 
		{
			System.out.println("### ROUND " + round);
			playHands(p1stack, p2stack, w1stack, w2stack);
			checkEmpty(p1stack, w1stack, p1Name, p2Name);
			checkEmpty(p2stack, w2stack, p2Name, p1Name);
		}
		
		if (round >= maxRound)
		{
			System.out.println("It's a draw. :/");
		}
	}

	private static Card[] makeDeck(int numberOfCards) {
		//Creates an array of Card objects called deck
		Card[] deck = new Card[numberOfCards];

		int cardIndex = 0;
		//Checks for which suit the card is
		for (int suit = 0; suit <= 3; suit++)
		{
			String n = "";
			char symbol = ' ';

			symbol = switch (suit) {
			case 0 -> '\u2660'; // SPADE
			case 1 -> '\u2666'; // DIAMOND
			case 2 -> '\u2663'; // CLUB
			case 3 -> '\u2764'; // HEART
			default -> ' ';
			};

			for(int val = 2; val <= 14; val++)
			{
				n = switch (val) {
				case 11 -> "J";
				case 12 -> "Q";
				case 13 -> "K";
				case 14 -> "A";
				default -> "" + val;
				};
				Card card = new Card(n, symbol, val);
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

	private static void playHands(Stack p1stack, Stack p2stack, Stack w1stack, Stack w2stack) {
		checkEmpty(p1stack, w1stack, p1Name, p2Name);
		checkEmpty(p2stack, w2stack, p2Name, p1Name);
		round++;
		Card c1 = p1stack.pop();
		Card c2 = p2stack.pop();

		System.out.printf(
				"%s%s\t%s%s\n",
				c1.getNumber(),
				c1.getSuit(),
				c2.getNumber(),
				c2.getSuit());

		int valP1 = c1.getValue();
		int valP2 = c2.getValue();
		Stack getAll = new Stack();

		if (valP1 > valP2) {
			// Push to P1's collection.
			System.out.println(" ^");
			w1stack.push(c1);
			w1stack.push(c2);
			System.out.println(p1Name + " +2 CARDS");
		} else if (valP1 < valP2) {
			// Push to P2's collection.
			System.out.println("\t^");
			w2stack.push(c2);
			w2stack.push(c1);
			System.out.println(p2Name + " +2 CARDS");
		} else {
			// We have a tie!
			System.out.println("We have a tie!");
			System.out.println("THIS");
			System.out.println("MEANS");
			System.out.println("WAR!!");
			boolean playing = true;
			while (playing)
			{
				checkEmpty(p1stack, w1stack, p1Name, p2Name);
				checkEmpty(p2stack, w2stack, p2Name, p1Name);
				
				getAll.push(c1);
				getAll.push(c2);

				checkEmpty(p1stack, w1stack, p1Name, p2Name);
				checkEmpty(p2stack, w2stack, p2Name, p1Name);
				Card t1 = p1stack.pop();
				Card t2 = p2stack.pop();
				getAll.push(t2);
				getAll.push(t1);

				System.out.printf(
						"%s%s\t%s%s\n",
						t1.getNumber(),
						t1.getSuit(),
						t2.getNumber(),
						t2.getSuit());

				checkEmpty(p1stack, w1stack, p1Name, p2Name);
				checkEmpty(p2stack, w2stack, p2Name, p1Name);
				Card t3 = p2stack.pop();
				Card t4 = p1stack.pop();

				getAll.push(t4);
				getAll.push(t3);


				System.out.printf(
						"%s%s\t%s%s\n",
						t3.getNumber(),
						t3.getSuit(),
						t4.getNumber(),
						t4.getSuit());

				checkEmpty(p1stack, w1stack, p1Name, p2Name);
				checkEmpty(p2stack, w2stack, p2Name, p1Name);
				Card t6 = p1stack.pop();
				Card t5 = p2stack.pop();

				valP1 = t6.getValue();
				valP2 = t5.getValue();

				System.out.printf(
						"%s%s\t%s%s\n",
						t6.getNumber(),
						t6.getSuit(),
						t5.getNumber(),
						t5.getSuit());

				if (valP1 > valP2) {
					// Push to P1's collection.
					System.out.println(" ^");
					moveStack(getAll, w1stack);
					w1stack.push(t5);
					w1stack.push(t6);
					System.out.println(p1Name + " +8 CARDS");
					playing = false;
				} else if (valP1 < valP2) {
					// Push to P2's collection.
					System.out.println("\t^");
					moveStack(getAll, w2stack);
					w2stack.push(t6);
					w2stack.push(t5);
					System.out.println(p2Name + " +8 CARDS");
					playing = false;
				} else {
					// We have a tie!
					playing = false;
					System.out.println("We have another tie!");
					checkEmpty(p1stack, w1stack, p1Name, p2Name);
					checkEmpty(p2stack, w2stack, p2Name, p1Name);
					playHands(p1stack, p2stack, w1stack, w2stack);
				}
			}
		}
	}

	/**
	 * This method checks whether a player has lost the game.
	 * If their pstack is empty but their wstack is not, then
	 * the win stack is flipped onto the play stack.
	 *
	 * This method can terminate the program.
	 *
	 * @param pstack:       Player's play stack.
	 * @param wstack:       Player's win stack.
	 * @param playerName:   Player's name.
	 * @param opponentName: Opponent's name.
	 */
	private static void checkEmpty(Stack pstack, Stack wstack, String playerName, String opponentName) {
		if (pstack.empty() && wstack.empty()) {
			System.out.println("Player " + playerName + " is out of cards!");
			System.out.println("Player " + opponentName + " wins the game!");
			System.out.println("Terminating the program. :)");
			System.exit(0); // EXIT.
		} else if (pstack.empty()) {
			System.out.println("Flipping stack for " + playerName + ".");
			int i = 0;
			while (wstack.notEmpty()) {
				i++;
				Card card = (Card) wstack.pop();
				pstack.push(card);
			}
			System.out.println(playerName + " CARDS REMAINING: " + i);
		}
	}

	/** UNUSED
	 * Moves cards from one stack to another.
	 *
	 * @param from: Stack to move from.
	 * @param to:   Stack to move to.
	 */
	private static void moveStack(Stack from, Stack to) {
		while (from.notEmpty()) {
			Card card = from.pop();
			to.push(card);
		}
	}

	/**
	 * Prints a stack without altering it.
	 *
	 * @param stack
	 */
	private static void printStack(Stack stack) {
		Stack acc = new Stack();
		while (stack.notEmpty()) {
			Card c = stack.pop();
			System.out.println(c.getNumber() + c.getSuit());
			acc.push(c);
		}

		while (acc.notEmpty()) {
			Card c = acc.pop();
			stack.push(c);
		}
	}

}
