/**Pat Eizenga
 * 10/2/2024
 * Purpose: To read client info from a csv file.
 */

/** The client class. Starts by declaring all variables.
 * 
 */
public class Client {
	private long accountNumber;
	private String firstName;
	private String lastName;
	private double balance;
	private Client next;
	
	/**Default Constructor
	 * Creates a client with no arguments/parameters
	 */
	public Client() {
		
	}
	
	/**Constructor code for creating the Client object
	 * @param accountNumber
	 * @param firstName
	 * @param lastName
	 * @param balance
	 */
	public Client(long accountNumber, String firstName, String lastName, double balance) {
		setAccountNumber(accountNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setBalance(balance);
	}
	


	/**Retrieves the account number
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}
	
	/**Changes the account number
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**Returns the first name of the Client.
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**Mutates the firstName of the Client
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**Accesses the last name of the Client
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**Rewrites the last name of the Client
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**Checks the Client's balance
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**Changes the Client's balance
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**For moving through a linkedlist of the file
	 * @return the next
	 */
	public Client getNext() {
		return next;
	}
	
	/**For changing values within the csv file.
	 * @param next the next to set
	 */
	public void setNext(Client next) {
		this.next = next;
	}

}
