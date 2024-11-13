/**
 * 
 */

/**
 * 
 */
public class Tree {
	Node root = new Node("");

	public boolean isLeaf(Node node)
	{
		if (node != null)
		{
			if (node.yes == null && node.no == null)
				return true;
			else
				return false;
		}
		else return false;
	}
}
