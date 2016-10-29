class Node {
	int page;
	int content;
	
	Node before;
	Node after;

	public Node(int page, int content) {
		this.page = page;
		this.content = content;
	}
}