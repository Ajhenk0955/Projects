class DynamicNode {
	private Object info;
	private DynamicNode next;

	public DynamicNode(Object x, DynamicNode n) {

		setInfo(x);
		setNext(n);
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public DynamicNode getNext() {
		return next;
	}

	public void setNext(DynamicNode next) {
		this.next = next;
	}

}