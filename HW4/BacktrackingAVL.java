import java.util.ArrayList;
import java.util.List;

public class BacktrackingAVL extends AVLTree
{
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL()
    {
        super();
    }
    
	//You are to implement the function Backtrack.
    public void Backtrack()
    {
    	if (!deque.isEmpty())
    	{  
	    	Object[] array = deque.pop();
	    	Node node = (Node) array[0];
	    	Node insertionNode = (Node) array[1];
	    	ImbalanceCases type = (ImbalanceCases) array[2];
	    	
	    	if (type == ImbalanceCases.LEFT_LEFT)
	    	{
	    		rotation(node,ImbalanceCases.RIGHT_RIGHT);	   		
	    	}
	    	else if (type == ImbalanceCases.RIGHT_RIGHT)
	    	{
	    		rotation(node,ImbalanceCases.LEFT_LEFT);
	    	}
	    	else if (type == ImbalanceCases.LEFT_RIGHT)
	    	{
	    		rotation(node,ImbalanceCases.LEFT_LEFT);
	    		rotation(node.left.left,ImbalanceCases.RIGHT_RIGHT);
	    	}
	    	else if (type == ImbalanceCases.RIGHT_LEFT)
	    	{   
	    		rotation(node,ImbalanceCases.RIGHT_RIGHT); 
	    		rotation(node.right.right,ImbalanceCases.LEFT_LEFT);  	
	    	}
	    		    	       	   	
	    	if (insertionNode == root) {root = null;}
	    	else if (insertionNode.parent.right == insertionNode) {insertionNode.parent.right = null;} 	
	    	else {insertionNode.parent.left = null;} 
	    	
	    	insertionNode.decrease();
	    		    	    	
	    	Node p = insertionNode.parent;
	    	while (p != null)
	    	{
	    		p.updateHeight();
	    		p = p.parent;
	    	}	
    	}    	
    }
      
    private void rotation(Node node, ImbalanceCases imbalanceCases)
    {
    	Node x = node.parent; 
    	if (imbalanceCases == ImbalanceCases.LEFT_LEFT)
    	{
    		if (x == root) {root = rotateLeft(x);}
    		else if (x.parent.left == x) {x.parent.left = rotateLeft(x);}
    		else if (x.parent.right == x) {x.parent.right = rotateLeft(x);}	 
    	}
    	else if (imbalanceCases == ImbalanceCases.RIGHT_RIGHT)
    	{
    		if (x == root) {root = rotateRight(x);}
    		else if (x.parent.left == x) {x.parent.left = rotateRight(x);}
    		else if (x.parent.right == x) {x.parent.right = rotateRight(x);}
    	}
    }
       
  
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample()
    {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		return list;
    }
    
    public int Select(int index)
    {
    	if (root != null) {return Select(root, index);} 
    	else {return -1;}
    }
    
    private int Select(Node node, int index)
    {	
    	if (node.size == 1) {return node.value;}
    	else if (node.left != null && index == node.left.size + 1) {return node.value;}  	
    	else if(node.right != null && index == node.size - node.right.size) {return node.value;}      	
    	else if (node.left != null && node.left.size >= index) {return Select(node.left, index);}    	
    	else if (node.right != null & node.left != null) {return Select(node.right, index-node.left.size-1);}
    	else {return Select(node.right, index-1);} 	    	  	
    }
    
    public int Rank(int value)
    {
    	if (root != null) {return Rank(root,0,value);}
    	else {return -1;}
    }
    
    private int Rank(Node node, int size, int value)
    {
    	if (node.value == value)
    	{
    		if (node.left != null) {return size + node.left.size;} 
    		else {return size;}   		
    	}
    	else if (node.value > value)
    	{
    		if (node.left != null) {return Rank(node.left,size,value);}
    		else {return size;}    		
    	}
    	else
    	{		
    		if (node.right != null) {return Rank(node.right,size+(node.size-node.right.size),value);}
    		else if (node.left != null) {return size + node.left.size + 1;}
    		else {return size+1;}
    	}     
    }
}
