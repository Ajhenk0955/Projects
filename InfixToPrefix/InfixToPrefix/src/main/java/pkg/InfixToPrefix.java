import java.util.Scanner;

public class InfixToPrefix {

	public static boolean isOperand(char symb) {
		if (symb == '+' || symb == '-' || symb == '*' || symb == '/'
				|| symb == '$' || symb == '(' || symb == ')') {
			return false;
		} else {
			return true;
		}
	}

	public static String infix_to_prefix(String infix) {
		// convert and return
		// the prefix of the infix string

		CharStack infixStack = new CharStack();
		String prefix = "";

		for (int i = infix.length() - 1; i >= 0; i--) {

			char tempChar = infix.charAt(i);

			if (isOperand(tempChar)) {
				// if letter place on line
				prefix += tempChar;
			} else if (tempChar == '(') {
				// manage parentheses
				tempChar = infixStack.pop();
				while (tempChar != ')') {

					prefix += tempChar;
					tempChar = infixStack.pop();
				}
			}

			else if (!isOperand(tempChar)) {
				if (infixStack.empty() || tempChar == ')') {
					// if operator and stack is empty push
					infixStack.push(tempChar);
					
				} else {
					// if operator and stack is not empty, compare
					char charTempTwo = 0;
					do {
						if (!infixStack.empty()) {
							charTempTwo = infixStack.peek();
							
							if (!precedence(tempChar, charTempTwo)) {
								prefix += infixStack.pop();
							} else {
								break;
							}
						} else {
							break;
						}
					} while (!precedence(tempChar, charTempTwo));

					// push current operator to stack
					infixStack.push(tempChar);
				}
			}

		}
		//clear stack and finish writing
		while (!infixStack.empty()) {
			prefix += infixStack.pop();

		}
		return new StringBuilder(prefix).reverse().toString();
	}

	public static boolean precedence(char op1, char op2) {

		// compare for precedence
		// op2 is the operator on top of stack, op1 is the incoming operator
		int opcode1, opcode2;
		/* opcode for + or - is 1 */
		/* opcode for * or / is 2 */
		/* opcode for $ is 3 */
		opcode1 = switchCase(op1);
		opcode2 = switchCase(op2);

		if (opcode1 >= opcode2)
			return true;
		else
			return false;
	}

	private static int switchCase(char operator) {
		/* opcode for + or - is 1 */
		/* opcode for * or / is 2 */
		/* opcode for $ is 3 */

		switch (operator) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '$':
			return 3;
		}
		return 0;
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String infix, preFix;
		System.out.println("Enter an infix string: ");

		// TO DO: read the infix string from the keyboard
		infix = scan.nextLine();

		preFix = infix_to_prefix(infix); // method to convert

		System.out.println("The prefix is " + preFix);
	}
}