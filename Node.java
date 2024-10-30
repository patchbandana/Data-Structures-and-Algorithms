/**Pat Eizenga
 * 10/30/2024
 * Purpose: Node class
 */

/**Node class
 */
public class Node {
	public String question = "";
	public Node yes = null;
	public Node no = null;
	
	/**
	 * Node constructor
	 * @param question posed
	 */
	public Node(String question)
	{
		this.question = question;
	}

}
