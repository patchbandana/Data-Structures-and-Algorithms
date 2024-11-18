/**Pat Eizenga
 * 10-25-2024
 * Purpose: To learn how to work with the data structure/algorithm binary trees
 * 	and the concept of recursion
 */

import java.util.Scanner;
/*Binary Tree practice checking which values are greater than or less than a given integer.
 */
public class BinaryTree {

	public static Node root = new Node(-1);

	/**The Binary Tree program. Practice and copypastable.
	 * @param args NOT USED
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		do
		{
			System.out.print("Enter a positive integer from 1 to 1000 (-1 to quit) : ");

			if (x > 0)
			{
				if (root.value == -1)
				{
					root.value = x;
				}
				else 
				{
					Node n = new Node(x);
					add(n, root);
				}
			}
		} while (x >= 0);
		//printTree(root);

		System.out.println("All Done");
		scanner.close();
	}

	public static void add(Node n, Node r)
	{
		if (n.value <= r.value)
		{
			if (r.lessThan != null)
			{
				add(n,r.lessThan);
			}
			else
			{
				r.lessThan = n;
			}
		} else
		{
			if (r.greaterThan != null)
			{
				add(n, r.greaterThan);
			}
			else
			{
				r.greaterThan = n;
			}
		}
	}
}

