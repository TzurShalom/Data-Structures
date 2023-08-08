import java.util.ArrayList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}
	
	//You are to implement the function Backtrack.
	public void Backtrack()
	{		
		if (!deque.isEmpty()) 
		{
			Object[] array; 
			array = deque.pop(); 
			Node<T> node = (Node) array[0];
			T value = (T) array[1];
			node.removeKey(value);
			if (node.numOfKeys == 0) {root = null;}
						
			while (deque.peek() != null && deque.peek()[2] == BTreeActionType.SPLIT)
			{	
				array = deque.pop();
				node = (Node) array[0];
				value = (T) array[1];
				int index = (int) array[3];
				
				if (node.parent.numOfKeys == 1) // root
				{
					root = node;
					node.parent = null;
				}
				else
				{
					Node p = node.parent;
					p.removeKey(value);
					p.removeChild(index);
					p.removeChild(index);
					p.addChild(node);					
				}						
			}							
		}
    }
						
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		return list;
	}
}
