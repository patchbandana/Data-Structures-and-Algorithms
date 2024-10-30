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
		if (node.yes == null && node.no == null)
			return true;
		else
			return false;
	}

}
