/**
 * binary sorted tree
 * Some methods are not needed, I did not go through the work of removing them. @insert & @balence
 * 
 */
public class SortedTree {
	public final static int SIZE_INPUT = 8;
	TreeNode root;
	int weight;// if we can add individual elements

	/**
	 * Receives a sorted sequence of integers to build a binary tree. Given any
	 * node nd in this binary tree, let its subtrees be denoted t1 and t2. The
	 * number of nodes in t1 and t2 differ by no more than one.
	 */
	public SortedTree(int[] input) {
		root = null;
		if (!verifyInput(input)) {// if input is not sorted return
			return;
		} else {
			root = buildTree(input, 0, input.length - 1);
		}

		weight = 0;
	}

	/**
	 * Builds a binary using the objects from input[start] to input[end].
	 * 
	 * See the constructor for the requirements for this binary tree
	 */
	private TreeNode buildTree(int[] input, int i, int j) {
		if (i > j) {
			return null;
		}
		int middle = i + (j - i) / 2;
		TreeNode newRoot = new TreeNode(input[middle]);
		newRoot.left = buildTree(input, i, middle - 1);
		newRoot.right = buildTree(input, middle + 1, j);
		this.root = newRoot;
		return root;
	}

	/**
	 * verifies input as an ordered array
	 * 
	 * @param input
	 * @return
	 */
	private boolean verifyInput(int[] input) {
		if (input == null) {
			return false;
		}

		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] >= input[i + 1]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * returns depth
	 * 
	 * @return
	 */
	public int depth() {
		return _depth(root, 0);

	}

	/**
	 * backend depth, calculates recursively
	 * 
	 * @param root
	 * @param d
	 * @return
	 */
	public int _depth(TreeNode root, int d) {
		int leftDepth = d, rightDepth = d;
		if (root.left != null) {
			leftDepth = _depth(root.left, d + 1);
		}
		if (root.right != null) {
			rightDepth = _depth(root.right, d + 1);
		}

		return leftDepth > rightDepth ? leftDepth : rightDepth;
	}

	/**
	 * recursively inserts int in the sorted binary tree
	 * 
	 * @param newInt
	 * @return
	 */
	public boolean insert(int newInt, TreeNode root) {
		if (root == null) {
			root = new TreeNode(newInt);
			return true;
		}
		if (root.getInfo() == newInt) {
			System.out.println("Duplicate");
			return false;
		}
		if (newInt < root.getInfo()) {
			insert(newInt, root.left);

			// insert left if can, else move down
			return true;
		} else {
			insert(newInt, root.right);
			// insert right if can, else move down
			return true;
		}
	}

	public void traverse() {
		traverseNodes(root);
	}

	/**
	 * prints tree in ascending order
	 * 
	 * @param root
	 */
	private void traverseNodes(TreeNode root) {
		if (root.left != null) {
			traverseNodes(root.left);
		}
		System.out.println(root.getInfo());
		if (root.right != null) {
			traverseNodes(root.right);
		}

	}

	public static void main(String args[]) {

		int[] input = new int[SIZE_INPUT];
		for (int i = 0; i < SIZE_INPUT; i++) {
			input[i] = i;
		}
		// another input array we saw in class:
		// int[] input = {4, 9, 15, 20, 22, 24, 35, 87};

		// 1 - create the binary search tree given the sorted input
		SortedTree st = new SortedTree(input);

		// 2 - print its depth
		System.out.println("The depth of the tree is " + st.depth());

		// 3 - print the tree nodes in ascending order
		st.traverse();
	}
}