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
		tree.root.question = "Elephant";
		String dataFile = "data.csv";
		
		Node current = tree.root;
		
		importTree(dataFile);

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
		deleteFile(dataFile);
		exportTree(tree.root, 1, dataFile);
		System.out.println("Goodbye.");
		scanner.close();
	} //end main
	
	/**deletefile prepares the output file for exporting by splitting,
	 * 	parsing, and reading in the data.
	 * 
	 * @param dataFile
	 */
	private static void deleteFile(String dataFile)
	{
		try
		{
			FileWriter fw = new FileWriter(dataFile, false);
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("File Write Error: " + dataFile);
		}
	}

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
	
	public static void importTree(String dataFile)
	{
		try
		{
			FileReader fr = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			String splitBy = ",";
			String[] data;
			ArrayList<String> questions = new ArrayList<>();
			
			while ((line = br.readLine()) != null)
			{
				data = line.split(splitBy);
				
				int index = Integer.parseInt(data[0]);
				
				if (questions.size() < index + 1)
				{
					for (int i = questions.size(); i <= index; i++)
					{
						questions.add("");
					}
				}
				questions.set(index, data[1]);
			}
			br.close();
		}
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
