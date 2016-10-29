class CharStack {

	private final int STACKSIZE = 100;
	private int top;
	private char[] items;

	public CharStack() {
		top = -1;
		items = new char[STACKSIZE];
	}

	public boolean empty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	public char pop() {
		if (empty()) {
			System.out.println("Stack underflow");
			System.exit(1);
		}
		return items[top--];
	}

	public void push(char x) {
		if (top == STACKSIZE) {
			System.out.println("Stack overflow");
			System.exit(1);
		}
		items[++top] = x;

	}

	public char peek() {
		if (empty()) {
			System.out.println("Stack underflow");
			System.exit(1);
		}
		return items[top];
	}
}