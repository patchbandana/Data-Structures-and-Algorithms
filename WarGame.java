/**Pat Eizenga
 * 10/16/2024
 * Purpose: To create the card game war as a text based program.
 */

import java.util.Random;
import java.util.Scanner;

/**
 * Modified War game to support multiple players without ArrayLists or HashMaps.
 * Now prompts the user for the number of players before starting.
 */
public class War {
    public static int numberOfPlayers; // Determined by user input
    public static int numberOfCards = 52;
    public static int round = 0;
    public static int maxRound = 10000; // Increased max rounds
    
    // Arrays to track card counts from previous rounds for stalemate detection
    public static int[] previousPlayerCardCounts;
    public static int[] previousWinCardCounts;
    public static int noChangeCounter = 0; // To detect stalemates

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (minimum 2): ");
        numberOfPlayers = Math.max(2, scanner.nextInt()); // Ensure at least 2 players
        
        // Initialize arrays to track card counts for stalemate detection
        previousPlayerCardCounts = new int[numberOfPlayers];
        previousWinCardCounts = new int[numberOfPlayers];

        boolean running = true;
        Card[] deck = makeDeck(numberOfCards);
        shuffle(deck);

        // Create stacks for players
        Stack[] playerStacks = new Stack[numberOfPlayers];
        Stack[] winStacks = new Stack[numberOfPlayers];
        
        for (int i = 0; i < numberOfPlayers; i++) {
            playerStacks[i] = new Stack();
            winStacks[i] = new Stack();
        }

        // Deal cards evenly among all players
        deal(deck, playerStacks);

        // Initialize previous card counts
        updateCardCountArrays(playerStacks, winStacks);

        while (running && round < maxRound) {
            System.out.println("### ROUND " + (round + 1));
            
            // Save the current state of card counts before playing
            int[] beforePlayerCounts = new int[numberOfPlayers];
            int[] beforeWinCounts = new int[numberOfPlayers];
            
            for (int i = 0; i < numberOfPlayers; i++) {
                beforePlayerCounts[i] = countCards(playerStacks[i]);
                beforeWinCounts[i] = countCards(winStacks[i]);
            }
            
            playHands(playerStacks, winStacks);
            
            // Add pause after battle to see winner
            promptEnterToContinue("Press Enter to continue after this battle...");
            
            checkElimination(playerStacks, winStacks);
            
            // Check if card counts changed after this round
            boolean cardCountsChanged = false;
            
            for (int i = 0; i < numberOfPlayers; i++) {
                int currentPlayerCount = countCards(playerStacks[i]);
                int currentWinCount = countCards(winStacks[i]);
                
                // If counts changed for any player, card distribution changed
                if (currentPlayerCount != beforePlayerCounts[i] || 
                    currentWinCount != beforeWinCounts[i]) {
                    cardCountsChanged = true;
                    break;
                }
            }
            
            // Update the stalemate detection logic
            if (!cardCountsChanged) {
                noChangeCounter++;
                System.out.println("No change in card distribution for " + noChangeCounter + " consecutive rounds.");
                
                if (noChangeCounter >= 10) {
                    System.out.println("Detected a stalemate - no change in card distribution for 10 consecutive rounds.");
                    System.out.println("Game ends in a draw.");
                    running = false;
                }
            } else {
                // Reset the counter if distribution changed
                if (noChangeCounter > 0) {
                    System.out.println("Card distribution changed, resetting stalemate counter.");
                }
                noChangeCounter = 0;
                
                // Update previous card counts
                updateCardCountArrays(playerStacks, winStacks);
            }
            
            // Check for a winner
            int activePlayers = 0;
            int lastActivePlayer = -1;
            
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!playerStacks[i].empty() || !winStacks[i].empty()) {
                    activePlayers++;
                    lastActivePlayer = i;
                }
            }
            
            if (activePlayers == 1) {
                System.out.println("Player " + (lastActivePlayer + 1) + " wins the game!");
                running = false;
            } else if (activePlayers == 0) {
                System.out.println("No players left with cards. It's a draw.");
                running = false;
            }
        }
        
        if (round >= maxRound) {
            System.out.println("Maximum rounds reached. It's a draw.");
            
            // Display remaining card counts for each player
            System.out.println("Final card counts:");
            for (int i = 0; i < numberOfPlayers; i++) {
                int playerCards = countCards(playerStacks[i]);
                int winCards = countCards(winStacks[i]);
                System.out.printf("Player %d: %d cards\n", i + 1, playerCards + winCards);
            }
        }
        
        scanner.close();
    }

    /**
     * Method to pause the game and wait for user input
     */
    private static void promptEnterToContinue(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Updates the arrays used to track card counts for stalemate detection
     */
    private static void updateCardCountArrays(Stack[] playerStacks, Stack[] winStacks) {
        for (int i = 0; i < numberOfPlayers; i++) {
            previousPlayerCardCounts[i] = countCards(playerStacks[i]);
            previousWinCardCounts[i] = countCards(winStacks[i]);
        }
    }

    private static Card[] makeDeck(int numberOfCards) {
        Card[] deck = new Card[numberOfCards];
        int index = 0;
        char[] suits = {'♠', '♦', '♣', '❤'};
        for (char suit : suits) {
            for (int val = 2; val <= 14; val++) {
                String number = switch (val) {
                    case 11 -> "J";
                    case 12 -> "Q";
                    case 13 -> "K";
                    case 14 -> "A";
                    default -> String.valueOf(val);
                };
                deck[index++] = new Card(number, suit, val);
            }
        }
        return deck;
    }

    private static void shuffle(Card[] deck) {
        Random random = new Random();
        for (int i = deck.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = deck[randomIndex];
            deck[randomIndex] = deck[i];
            deck[i] = temp;
        }
    }

    /**
     * Counts the number of cards in a stack
     * @param stack The stack to count
     * @return The number of cards in the stack
     */
    private static int countCards(Stack stack) {
        if (stack.empty()) {
            return 0;
        }
        
        int count = 0;
        Card current = stack.head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    private static void deal(Card[] deck, Stack[] playerStacks) {
        for (int i = 0; i < deck.length; i++) {
            playerStacks[i % numberOfPlayers].push(deck[i]);
        }
    }

    private static void playHands(Stack[] playerStacks, Stack[] winStacks) {
        round++;
        Stack playedCards = new Stack();
        
        // Check if any players can play
        boolean canPlay = false;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (playerStacks[i].notEmpty() || winStacks[i].notEmpty()) {
                canPlay = true;
                break;
            }
        }
        
        if (!canPlay) return;
        
        // Ensure all players who need cards move them from win stack to play stack
        for (int i = 0; i < numberOfPlayers; i++) {
            if (playerStacks[i].empty() && winStacks[i].notEmpty()) {
                System.out.printf("Player %d reshuffling win pile into play pile\n", i + 1);
                while (winStacks[i].notEmpty()) {
                    playerStacks[i].push(winStacks[i].pop());
                }
            }
        }
        
        // Play cards
        int highestValue = -1;
        Stack tiedPlayers = new Stack();
        
        for (int i = 0; i < numberOfPlayers; i++) {
            if (playerStacks[i].notEmpty()) {
                Card played = playerStacks[i].pop();
                played.setNext(null); // Ensure the card is disconnected from others
                playedCards.push(played);
                System.out.printf("Player %d: %s%s\n", i + 1, played.getNumber(), played.getSuit());
                
                if (played.getValue() > highestValue) {
                    highestValue = played.getValue();
                    tiedPlayers = new Stack(); // Reset tied players
                    tiedPlayers.push(new Card("", (char) (i + '0'), highestValue));
                } else if (played.getValue() == highestValue) {
                    tiedPlayers.push(new Card("", (char) (i + '0'), highestValue));
                }
            }
        }
        
        if (tiedPlayers.empty()) return; // No valid play
        
        // Check for a war (tie)
        int tiedPlayerCount = countCards(tiedPlayers);
        
        if (tiedPlayerCount > 1) {
            System.out.println("WAR! Players tied with value: " + highestValue);
            handleWar(tiedPlayers, playerStacks, winStacks, playedCards);
        } else {
            // No war, determine winner
            Card winnerCard = tiedPlayers.pop();
            int winner = winnerCard.getSuit() - '0';
            
            System.out.printf("Player %d wins this round with value %d!\n", 
                             winner + 1, winnerCard.getValue());
            
            // Winner gets all cards
            while (playedCards.notEmpty()) {
                Card card = playedCards.pop();
                card.setNext(null); // Ensure card is disconnected
                winStacks[winner].push(card);
            }
        }
    }

    private static void handleWar(Stack tiedPlayers, Stack[] playerStacks, Stack[] winStacks, Stack warPile) {
        // Copy tied players to a new stack for processing
        Stack warPlayers = new Stack();
        while (tiedPlayers.notEmpty()) {
            warPlayers.push(tiedPlayers.pop());
        }
        
        // Create a new stack for players who can continue the war
        Stack activeTiedPlayers = new Stack();
        
        // Have tied players put down a face-down card if able
        while (warPlayers.notEmpty()) {
            int playerIndex = warPlayers.pop().getSuit() - '0';
            
            // Make sure player has cards; if not, try to refill from win stack
            if (playerStacks[playerIndex].empty() && winStacks[playerIndex].notEmpty()) {
                System.out.printf("Player %d reshuffling win pile into play pile during war\n", playerIndex + 1);
                while (winStacks[playerIndex].notEmpty()) {
                    playerStacks[playerIndex].push(winStacks[playerIndex].pop());
                }
            }
            
            if (playerStacks[playerIndex].notEmpty()) {
                Card faceDown = playerStacks[playerIndex].pop();
                faceDown.setNext(null);
                warPile.push(faceDown);
                System.out.printf("Player %d places a face-down card for war\n", playerIndex + 1);
                
                // This player can continue in the war
                activeTiedPlayers.push(new Card("", (char) (playerIndex + '0'), 0));
            } else {
                System.out.printf("Player %d cannot continue in the war (no cards)\n", playerIndex + 1);
            }
        }
        
        // Now have the still-tied players play a face-up card
        Stack newTiedPlayers = new Stack();
        int highestValue = -1;
        
        while (activeTiedPlayers.notEmpty()) {
            int playerIndex = activeTiedPlayers.pop().getSuit() - '0';
            
            if (playerStacks[playerIndex].notEmpty()) {
                Card faceUp = playerStacks[playerIndex].pop();
                faceUp.setNext(null);
                warPile.push(faceUp);
                System.out.printf("Player %d plays %s%s for war\n", 
                                 playerIndex + 1, faceUp.getNumber(), faceUp.getSuit());
                
                if (faceUp.getValue() > highestValue) {
                    highestValue = faceUp.getValue();
                    newTiedPlayers = new Stack();
                    newTiedPlayers.push(new Card("", (char) (playerIndex + '0'), highestValue));
                } else if (faceUp.getValue() == highestValue) {
                    newTiedPlayers.push(new Card("", (char) (playerIndex + '0'), highestValue));
                }
            }
        }
        
        // Check the result of this war round
        if (newTiedPlayers.empty()) {
            // No players were able to play - split the cards among players still in the game
            System.out.println("War ended with no clear winner. Splitting cards.");
            distributeWarCards(warPile, playerStacks, winStacks);
            return;
        }
        
        int tiedPlayerCount = countCards(newTiedPlayers);
        
        if (tiedPlayerCount > 1) {
            // Another tie; continue the war
            System.out.println("War continues! Still tied with value: " + highestValue);
            handleWar(newTiedPlayers, playerStacks, winStacks, warPile);
        } else {
            // War is resolved; winner takes all
            int winner = newTiedPlayers.pop().getSuit() - '0';
            System.out.printf("Player %d wins the war with value %d!\n", winner + 1, highestValue);
            
            while (warPile.notEmpty()) {
                Card card = warPile.pop();
                card.setNext(null);
                winStacks[winner].push(card);
            }
        }
    }
    
    private static void distributeWarCards(Stack warPile, Stack[] playerStacks, Stack[] winStacks) {
        // Find all active players
        boolean[] activePlayers = new boolean[numberOfPlayers];
        int activeCount = 0;
        
        for (int i = 0; i < numberOfPlayers; i++) {
            activePlayers[i] = !playerStacks[i].empty() || !winStacks[i].empty();
            if (activePlayers[i]) {
                activeCount++;
            }
        }
        
        if (activeCount == 0) return;
        
        // Distribute the war cards among active players
        int cardIndex = 0;
        while (warPile.notEmpty()) {
            Card card = warPile.pop();
            
            // Find the next active player
            int playerIndex;
            int count = 0;
            do {
                playerIndex = cardIndex % numberOfPlayers;
                cardIndex++;
                count++;
                if (count > numberOfPlayers) break; // Safety check
            } while (!activePlayers[playerIndex]);
            
            if (count <= numberOfPlayers) {
                card.setNext(null);
                winStacks[playerIndex].push(card);
            }
        }
    }

    private static void checkElimination(Stack[] playerStacks, Stack[] winStacks) {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (playerStacks[i].empty() && winStacks[i].empty()) {
                System.out.printf("Player %d is out of the game!\n", i + 1);
                // Add pause after a player is eliminated
                promptEnterToContinue("Press Enter to continue after player elimination...");
            }
        }
    }
}