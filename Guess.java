/**Pat Eizenga
 * 10/30/2024
 * Purpose: Akinator type/20 Questions game
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**Akinator style question guessing game.
 * 
 */
public class Guess {

	/** Main method of Guess.
	 * @param args NOT USED
	 */
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		Scanner scanner = new Scanner(System.in);
		//tree.root.question = "Elephant";
		String dataFile = "data.csv";
		importTree(tree, dataFile);
		
		if (tree.root == null) {
		    // Fallback: initialize with a default node if the root is missing
		    System.out.println("Root node not found in data file, initializing default root node.");
		    tree.root = new Node("Elephant");  // Default initial question or animal
		}
		
		Node current = tree.root;

		boolean playing = true;

		
		String outstring = "";
		String response = "";
		char answer = ' ';

		while (playing)
		{
			if (tree.isLeaf(current))
			{
				outstring = "Are you a " + current.question + "?";
			}
			else
			{
				outstring = current.question;
			}//end if/else 
			System.out.println(outstring + " (Y/N/Q?) >");
			response = scanner.next();
			answer = response.charAt(0);

			switch(answer)
			{
			case 'Y':
			case 'y':
				if (tree.isLeaf(current))
				{
					System.out.println("I am so smart. I bet I can beat you again. \n");
					playing = false;
				}
				else
					current = current.yes;
				break;
			case 'N':
			case 'n':
				if (tree.isLeaf(current))
				{
					scanner.nextLine(); // clear buffer
					System.out.println("What is your animal?");
					String animal = scanner.next();
					scanner.nextLine(); // clear buffer
					System.out.println("Enter a question that determines a " + animal +
							" from a " + current.question + ".");
					String newQuestion = scanner.nextLine();
					System.out.println("Is the answer for a " + animal 
							+ " yes or no? (Y/N) > ");
					String yesNo = scanner.nextLine();

					Node newAnimal = new Node(animal);
					Node oldAnimal = new Node(current.question);

					current.question = newQuestion;

					if (yesNo.charAt(0) == 'y' || yesNo.charAt(0) == 'Y')
					{
						current.yes = newAnimal;
						current.no = oldAnimal;
					}
					else
					{
						current.yes = oldAnimal;
						current.no = newAnimal;
					}
					//Play Again
					current = tree.root;
					System.out.println("\n\nLet's play again!");
				}
				
				else 
					current = current.no;
				break;

			case 'q':
			case 'Q':
				playing = false;
				break;

			default:
				System.out.print("*** Y/N/Q Only!!! ****");
				break;
			}
		}//end while
		exportTree(tree.root, 1, dataFile);
		System.out.println("Goodbye.");
		scanner.close();
	} //end main

	public static void exportTree (Node r, int nodeID, String dataFile)
	{
		if (r.yes != null)
		{
			exportTree(r.yes, nodeID * 2, dataFile);
		}
		System.out.println(nodeID + " " + r.question);
		writeToFile(dataFile, nodeID, r.question);
		if (r.no != null)
		{
			exportTree(r.no, nodeID * 2 + 1, dataFile);
		}
	}
	
	public static void importTree(Tree tree, String dataFile)
	{
		try
		{
			FileReader fr = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			String splitBy = ",";
			ArrayList<Node> questions = new ArrayList<>();
			
			/*
			 * Initialize the array list with null values up to an index
			 * 	placeholder. Index 0 will be unused for 1-based indexing
			 */
			
			questions.add(null);
			
			
			while ((line = br.readLine()) != null)
			{
				String[] data = line.split(splitBy);
				
				/*
				 * Learned about trim method while googling the problem
				 * This eliminates the need to clear the buffer like with 
				 * the user input in the main method.
				 */
				
				int index = Integer.parseInt(data[0].trim());
				String question = data[1].trim();
				
				//Ensures the list is large enough for current index
				while (questions.size() <= index)
				{
						questions.add(null);
				}
				
				//Create the node in the binary tree and place it at the index
				questions.set(index, new Node(question));
			}
			
			br.close();
			
			//Assign the new root node
			if (questions.size() > 1) {
	            tree.root = questions.get(1);
	        }
			//Root node at position 1 rather than 0 for placeholding
			//Link nodes based on binary tree relationships
			for (int i = 1; i < questions.size(); i++)
			{
				Node node = questions.get(i);
				if (node != null)
				{
					if (2 * i < questions.size() && questions.get(2 * i) != null) 
					{
						node.yes = questions.get(2 * i);
						//This is the left "child" of the binary tree
					} //end nested if
					if (2 * i + 1 < questions.size() && questions.get(2 * i + 1) != null) 
					{
						node.no = questions.get(2 * i + 1);
						//This is the right "child" of the new binary tree
					} //end nested else if
				} //end if
			} // end for loop
		}// end try
		catch (IOException e)
		{
			System.out.println("File Read Error: " + dataFile);
		}
	}

	public static void writeToFile(String dataFile, int nodeID, String question)
	{
		try
		{
			FileWriter fw = new FileWriter(dataFile, true);
			String outString = String.format("%d,  %s, \n", nodeID, question);
			fw.write(outString);
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("File Read Error!");
		}
	}

}//end program
