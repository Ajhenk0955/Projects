/**
 * individual tree elements
 * 
 */
class TreeNode {
	private int info;
	public TreeNode left, right;

	public TreeNode(int x) {
		setInfo(x);
		left = right = null;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

}