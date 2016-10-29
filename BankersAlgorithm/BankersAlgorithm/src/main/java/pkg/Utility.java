
import java.util.*;
import java.util.concurrent.*;

/* no checking for null pointers*/
public class Utility {
	public static Random rand = new Random();

	public static void subtract(int[] a1, int[] a2) {
		if (a1.length == a2.length) {
			for (int i = 0; i < a1.length; i++) {
				a1[i] -= a2[i];
			}
		}
	}

	public static void add(int[] a1, int[] a2) {
		if (a1.length == a2.length) {
			for (int i = 0; i < a1.length; i++) {
				a1[i] += a2[i];
			}
		}
	}

	public static boolean isLessThanOrEqualTo(int[] a1, int[] a2) {
		if (a1.length == a2.length) {
			for (int i = 0; i < a1.length; i++) {
				if (a1[i] > a2[i]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void copyArray(int[] a1, int[] a2) {
		if (a1.length == a2.length) {
			for (int i = 0; i < a1.length; i++) {
				a1[i] = a2[i];
			}
		}
	}

	/*
	 * a1 will be randomly populated. a1[i] is in the range of [ 0, max[i] ]
	 */
	public static void randomize(int[] a1, int[] max) {
		if (a1.length == max.length) {
			for (int i = 0; i < a1.length; i++) {
				if (max[i] > 0) {
					a1[i] = rand.nextInt(max[i] + 1);
				} else {
					a1[i] = 0;
				}
			}
		}

	}

	public static boolean isZero(int[] a) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean zeroOut(int[] a) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				a[i] = 0;
			}
		}
		return true;
	}

	public static void printArray(int[] a) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + ", ");
			}
			System.out.println();
		}
	}

	public static String arrayToString(int[] a) {
		String s = "";
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				s += a[i] + ",";
			}

		}
		return s;
	}
}
