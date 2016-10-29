
// Correct output: 5,2,7,1,2,4,-1,-1,11,12,-1,8

public class PageReplacementShortTest {
	public static final int CAPACITY = 10;

	public static void main(String args[]) {
		test(new MemorySpace(CAPACITY));
	}

	private static void test(MemorySpace stack) {
		for (int i = 0; i < 10; i++) {
			stack.update(i, i);
		}

		System.out.printf("%d,", stack.read(5));
		System.out.printf("%d,", stack.read(2));
		System.out.printf("%d,", stack.read(7));
		System.out.printf("%d,", stack.read(1));
		System.out.printf("%d,", stack.read(2));
		System.out.printf("%d,", stack.read(4));
		stack.update(11, 11);
		System.out.printf("%d,", stack.read(0));
		stack.update(12, 12);
		System.out.printf("%d,", stack.read(3));
		stack.update(8, 8);
		System.out.printf("%d,", stack.read(11));
		System.out.printf("%d,", stack.read(12));
		stack.update(13, 13);
		stack.update(14, 14);
		System.out.printf("%d,", stack.read(6));
		System.out.printf("%d", stack.read(8));
	}
}
