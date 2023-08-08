import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;
    private Boolean usingStack = true;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    // The method returns the root of the underlying BST.
    public Node getRoot()
    {
        if (root == null)
        {
            throw new NoSuchElementException("empty tree has no root");
        }
        return root;
    }

    // The method return the node that contains the key k, and null if not found.
    public Node search(int k)
    {
        if(root == null) {return null;}
        Node node = root;
        while (node != null && node.getKey() != k)
        {
            if (k < node.getKey()) {node = node.left;}
            else {node = node.right;}
        }
        return node;
    }

    // The method insert the node to the tree as a leaf.
    public void insert(Node node)
    {
        if (node == null) {throw new IllegalArgumentException();}

        if (usingStack)
        {
            Object[] array = {1, 0, node, null};
            stack.push(array);
        }

        if(root == null) {root = node;}
        else {root.insert(node);}
    }

    // The method delete the node from the tree iteratively.
    public void delete(Node node)
    {
        if (node == null) {throw new IllegalArgumentException();}

        int n;
        Node replacementNode = null;
        Node Parent = null;
        Node Right = null;
        Node Left = null;
        
        if (node.left == null && node.right == null) // leaf
        {
            n = 0;
            if (node == root) {root = null;} // case 1 : Delete root
            else if (node.parent.right == node) {node.parent.right = null;} // case 2 : Delete right son
            else {node.parent.left = null;} // case 3 : Delete left son
        }
        else if (node.left == null | node.right == null) // father for one son
        {        	        	        	            	
            n = 1;
            Parent = node; 
        	if (node.right != null)
            {
                if (node == root) {root = node.right;}                                         
                else if (node.parent.right == node) {node.parent.right = node.right;}
                else {node.parent.left = node.right;} 
                
                node.right.parent = node.parent;               
                replacementNode = node.right;               
            }
            else
            {
                if (node == root) {root = node.left;}
                else if (node.parent.right == node) {node.parent.right = node.left;}
                else {node.parent.left = node.left;}
                
                node.left.parent = node.parent;                
                replacementNode = node.left;              
            }
        } 
        else // father for two sons
        {
        	n = 2;
        	if (node.right.left == null)
        	{       		
        		Node successor = node.right;
        		if (node == root) {root = successor;}
        		else
        		{
                	if (node.parent.right == node) {node.parent.right = successor;}
                	else {node.parent.left = successor;}
        		}
        		
                successor.parent = node.parent;
                successor.left = node.left;
                successor.right = node.right.right;     
                
                node.left.parent = successor;                
               
                replacementNode = successor;
        	}
        	else
        	{
        		Node successor = node.right;
        		Node successorParent = node;
                while (successor.left != null)
                {
                	successorParent = successor;
                	successor = successor.left;
                }
                 
                Parent = successorParent;
                Right = successor.right;
                
                successorParent.left = successor.right;
                
                successor.parent = node.parent;
                successor.left = node.left;
                successor.right = node.right;
                               
                if (node == root) {root = successor;}               
                else
        		{
                	if (node.parent.right == node) {node.parent.right = successor;}
                	else {node.parent.left = successor;}
            	}
              
                node.left.parent = successor;                
                node.right.parent = successor;
                
                replacementNode = successor;

        	}        	       	        	
        }
        if (usingStack)
        {
            Object[] array = {0, n, replacementNode, node, Parent, Right, Left};
            stack.push(array);
        }
    }

    // The method find and return if exist the minimum node in the tree.
    public Node minimum()
    {
        if (root == null) {throw new NullPointerException();}
        Node node = root;
        while (node.left != null) {node = node.left;}
        return node;
    }

    // The method find and return if exist the maximum node in the tree.
    public Node maximum()
    {
        if (root == null) {throw new NullPointerException();}
        Node node = root;
        while (node.right != null) {node = node.right;}
        return node;
    }

    // The method find and return if exist the successor node of the receiving node.
    public Node successor(Node node)
    {
        if (node == maximum() || root == null) {throw new NullPointerException();}
        return node.successor();
    }

    // The method find and return if exist the predecessor node of the receiving node.
    public Node predecessor(Node node)
    {
        if (node == minimum() || root == null) {throw new NullPointerException();}
        return node.predecessor();
    }

    // cancels the last data manipulating action performed by the data structure.
    @Override
    public void backtrack()
    {
        if (!stack.isEmpty())
        {
            usingStack = false;
            Object[] array = (Object[]) stack.pop();
            redoStack.push(array);

            if ((int) array[0] == 1) {delete((Node) array[2]);}
            else
            {
                if ((int) array[1] == 0) {insert((Node) array[3]);}                              
                else if ((int) array[1] == 1)
                {
                    Node CurrentNode = (Node) array[2];
                    Node PreviousNode = (Node) array[3];
                    
                    if (root == CurrentNode) {root = PreviousNode;}
                    else
            		{
                    	if (CurrentNode.parent.right == CurrentNode) {CurrentNode.parent.right = PreviousNode;}
                    	else {CurrentNode.parent.left = PreviousNode;}
                    	CurrentNode.parent = PreviousNode;
                	}                                                           
                }
                
                else 
                {
                    Node CurrentNode = (Node) array[2];
                    Node PreviousNode = (Node) array[3];
                                       
                    if (root == CurrentNode) {root = PreviousNode;}
                    else
            		{
                    	if (CurrentNode.parent.right == CurrentNode) {CurrentNode.parent.right = PreviousNode;}
                    	else {CurrentNode.parent.left = PreviousNode;}
                	}
                           
                	if (CurrentNode.left != null) {CurrentNode.left.parent = PreviousNode;}
                	if (CurrentNode.right != null) {CurrentNode.right.parent = PreviousNode;}
                                
                	CurrentNode.parent = (Node) array[4];
                	CurrentNode.right = (Node) array[5];
                	CurrentNode.left = (Node) array[6];
                	
                	if (CurrentNode.parent != null)
                	{
                		if (CurrentNode.parent.getKey() > CurrentNode.getKey()) {CurrentNode.parent.left = CurrentNode;}
                		else {CurrentNode.parent.right = CurrentNode;}
                	}
                	
                	if (CurrentNode.left != null) {CurrentNode.left.parent = CurrentNode;}
                	if (CurrentNode.right != null) {CurrentNode.right.parent = CurrentNode;}               	
                }
            }
            usingStack = true;
    	}
    }
    

    // The method cancels the cancellation of the last insert or delete.
    @Override
    public void retrack()
    {
        if (!redoStack.isEmpty())
        {
            usingStack = false;
            Object[] array = (Object[]) redoStack.pop();
            if ((int) array[0] == 1)
            {
                insert((Node) array[2]);
            }
            else
            {
                delete((Node) array[3]);
            }
        }
        usingStack = true;
    }

    // The method print in preOrder the keys in the tree.
    public void printPreOrder() {
        if (root != null) {
            root.printPreOrder();
            System.out.println(); // move on to the next line
        }
    }

    // The method calling to a method that print in preOrder the keys in the tree.
    @Override
    public void print() {
        printPreOrder();
    }
    
    public static class Node {
        // These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;

        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        // The method print in preOrder the keys in the tree.
        public void printPreOrder() {
            System.out.print(key + " ");
            if (left != null) {
                left.printPreOrder();
            }
            if (right != null) {
                right.printPreOrder();
            }
        }

        // The method insert the node to the tree as a leaf.
        public void insert(Node node){
            if(getKey()>node.getKey()){
                if(left==null) {
                    left = node;
                    node.parent = this;
                }
                else
                    left.insert(node);
            }
            else if(getKey()< node.getKey()){
                if(right==null) {
                    right = node;
                    node.parent = this;
                }
                else
                    right.insert(node);
            }
        }

        // The method find and return the successor node of this node.
        public Node successor() {
            Node output = null;
            if (right != null) {
                output = right;
                while (output.left != null) {
                    output = output.left;
                }
            } else {
                output = parent;
                Node current = this;
                while (output != null && current == output.right) {
                    current = output;
                    output = output.parent;
                }
            }
            return output;
        }

        // The method find and return the predecessor node of this node.
        public Node predecessor() {
            Node output = null;
            if (left != null) {
                output = left;
                while (output.right != null) {
                    output = output.right;
                }
            } else {
                output = parent;
                Node current = this;
                while (output != null & current == output.left) {
                    current = output;
                    output = output.parent;
                }
            }
            return output;
        }        
    }
}
