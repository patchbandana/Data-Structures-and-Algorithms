/**Pat Eizenga
 * 10/2/2024
 * Purpose: A Banking program.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**The main Banker class
 */
public class Banker {

	public static Scanner scanner = new Scanner(System.in);

	/**The main method of the program
	 * @param args NOT USED
	 */
	public static void main(String[] args) {


		//comma separated value file, already provided
		String filename = "clients.csv";

		//Creates new LinkedList object
		LinkedList ll = new LinkedList();
		Queue lobbyQueue = new Queue();

		//Feeds the file to the LinkedList class
		initialize(filename, ll);

		//ll.print();

		mainMenu(lobbyQueue, ll);
	}

	public static void mainMenu(Queue lobbyQueue, LinkedList ll) {

		int choice = 0;

		while (choice != 3)
		{
			System.out.println("\n\n*******************");
			System.out.println("*** MAIN MENU *****");
			System.out.println("*******************");
			System.out.println("Options: ");
			System.out.println("1. Add Client to Queue");
			System.out.println("2. Serve Client");
			System.out.println("3. Close Bank");
			System.out.println("*******************");
			System.out.print("\tCHOICE: ");

			choice = scanner.nextInt();

			switch(choice)
			{
			case 1:
				menu(lobbyQueue);
				break;
			case 2:
				if (lobbyQueue.peek() != null)
				{
					Client client = lobbyQueue.serve();
					tellerMenu(client, ll);
				}
				else 
					System.out.println("Queue empty!");
				break;
			case 3:
				break;
			default:
				System.out.println("Not a valid choice!\n\n");
				break;	
			}
		}
	}

	public static void tellerMenu(Client client, LinkedList ll)
	{
		int choice = 0;

		while (choice != 5)
		{
			System.out.println("*******************");
			System.out.println("***TELLER WINDOW***");
			System.out.println("*******************");
			System.out.println("*SERVING: " + client.getFirstName() + " " 
					+ client.getLastName() + "*");
			System.out.println("Options: ");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdrawal");
			System.out.println("4. Close Account");
			System.out.println("5. Client Complete");
			System.out.println("*******************");
			System.out.print("\tCHOICE: ");

			choice = scanner.nextInt();

			switch(choice)
			{
			case 1:
				balance(client, ll);
				break;
			case 2:
				deposit(client, ll);
				break;
			case 3:
				withdrawal(client, ll);
				break;
			case 4:
				closeAccount(client, ll);
				break;
			case 5: 
				break;
			default:
				System.out.println("Not a valid choice!\n\n");
				break;	
			}
		}
	}

	private static void closeAccount(Client client, LinkedList ll) {


	}

	private static void withdrawal(Client client, LinkedList ll) {


	}

	private static void deposit(Client client, LinkedList ll) {


	}

	private static void balance(Client client, LinkedList ll) {
		Client fromList = ll.find(client.getAccountNumber());

		if (fromList != null)
		{
			if ((client.getFirstName().equalsIgnoreCase(fromList.getFirstName())) &&
					(client.getLastName().equalsIgnoreCase(fromList.getLastName())))
			{
				System.out.printf("\n\nCurrent Balance : $%1.2f\n\n", fromList.getBalance());
			}
			else
			{
				System.out.println("\n\n*** Identity does not match account! ***\n\n");
			}
		}
		else 
		{
			System.out.println("\n\n *** Account number not found ***\n\n");
		}
	}


public static void menu(Queue lobbyQueue) {
	Client client = new Client();
	System.out.println("********************");
	System.out.println("Account Number: ");
	client.setAccountNumber(scanner.nextLong());
	System.out.println("First Name: ");
	client.setFirstName(scanner.next());
	System.out.println("Last Name: ");
	client.setLastName(scanner.next());

	lobbyQueue.add(client);

	/*
		System.out.println("Balance: ");
		client.setBalance(scanner.nextDouble());
	 */

}

public static void initialize(String filename, LinkedList ll)
{
	String line = "";
	String splitBy = ",";
	int count = 0;
	try
	{
		//FileReader requests file from the operating system and returns the location/handle
		FileReader fr = new FileReader(filename);
		//BufferedReader allows you to operate with the data in the file
		BufferedReader br = new BufferedReader(fr);

		while (((line = br.readLine()) != null))
		{
			String[] data = line.split(splitBy);
			long accountNumber = Long.parseLong(data[0]);
			String firstName = data[1];
			String lastName = data[2];
			double balance = Double.parseDouble(data[3]);


			//Creates a new reading of the file
			Client c = new Client(accountNumber, firstName, lastName, balance);

			//Adds the reading to the Linked List, looping through until the end of the file
			ll.add(c);
			count++;

		}
		System.out.println(count + " records read.\n");

		br.close();
	}
	catch(IOException e)
	{
		System.out.println("File Error: " + filename);
	}
}
}
